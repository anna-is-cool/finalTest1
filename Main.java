package final_test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File("/Users/ann/IdeaProjects/secondSemester1/src/final_test/schedule.txt"));
        List<String> list = new ArrayList<>();
        while (s.hasNext()){
            list.add(s.nextLine());
        }
        s.close();

        Map<String, List<Programme>> map = new HashMap<>();
        List<Programme> prList;
        String currentChannel = "";
        int index = 0;
        while(index < list.size()){
            currentChannel = list.get(index);
            index++;
            prList = new ArrayList<>();
            while(index < list.size() && list.get(index).charAt(0) != '#'){
                prList.add(new Programme(currentChannel, list.get(index), list.get(index + 1)));
                index += 2;
            }
            map.put(currentChannel, prList);
        }

        List<Programme> allProgramme = new ArrayList<>();
        for (List<Programme> l : map.values()){
            allProgramme.addAll(l);
        }
        System.out.println(Arrays.toString(allProgramme.toArray()));

        Collections.sort(allProgramme, new ProgrammeChannelComparator().thenComparing(new ProgrammeTimeComparator()));
        System.out.println(Arrays.toString(allProgramme.toArray()));


        programmesOnStream(map, new BroadcastsTime("15:33"));

        List<Programme> byName = findByProgrammeName("Новости", allProgramme);

        programmesOnStreamByChannel(map, new BroadcastsTime("18:44"), "#Первый");

        List<Programme> listTime = findByStreamingTime(allProgramme, new BroadcastsTime("18:00"), new BroadcastsTime("22:00"), "#Карусель");
        System.out.println(Arrays.toString(listTime.toArray()));

    }
    public static List<Programme> findByProgrammeName(String str, List<Programme> list){
        List<Programme> result = new ArrayList<>();
        for (Programme pr : list) {
            if (pr.getName().contains(str)){
                result.add(pr);
            }
        }
        return result;
    }
    public static List<Programme> findByStreamingTime(List<Programme> list, BroadcastsTime o1, BroadcastsTime o2, String channel){
        List<Programme> atm = new ArrayList<>();
        for (Programme pr : list){
            if (pr.getChannel().equals(channel)){
                if (pr.getTime().between(o1, o2)){
                    atm.add(pr);
                }
            }
        }
        return atm;
    }

    public static void programmesOnStreamByChannel(Map<String, List<Programme>> map, BroadcastsTime time, String channelName) {
        for (List<Programme> ls : map.values()) {
            for (int i = 1; i < ls.size(); i++) {
                if (time.between(ls.get(i - 1).getTime(), ls.get(i).getTime()) && ls.get(i - 1).getChannel().equals(channelName)) {
                    System.out.println(ls.get(i - 1));
                }
            }
        }
    }

    public static void programmesOnStream(Map<String, List<Programme>> map, BroadcastsTime time){
        for (List<Programme> ls : map.values()){
            for (int i = 1; i < ls.size(); i++){
                if (time.between(ls.get(i - 1).getTime(), ls.get(i).getTime())){
                    System.out.println(ls.get(i - 1));
                }
            }
        }
    }
    static class ProgrammeChannelComparator implements Comparator<Programme> {
        @Override
        public int compare(Programme p1, Programme p2) {
            return p1.getChannel().compareTo(p2.getChannel());
        }
    }

    static class ProgrammeTimeComparator implements Comparator<Programme> {
        @Override
        public int compare(Programme p1, Programme p2) {
            return p1.getTime().compareTo(p2.getTime());
        }
    }
}
