public class serch {

    public void serach(int num,int[] data) {
        System.out.println(data.length);

        int min = 0;
        int max = data.length-1;
        while (min < max) {
            int c = min + (max-min)/2;
            System.out.println(data[c] + "aaaa");
            if (data[c] == num) {
              System.out.println("見つかりました");
              break;
            } else if (data[c] >= num) {
                max = c;
                System.out.println(data[c]+"a");
                System.out.println(min + "b");
            } else if(data[c] < num){
                min = c;
                System.out.println(data[c] + "c");
                System.out.println(max + "d");
            }
        }

    }
}
