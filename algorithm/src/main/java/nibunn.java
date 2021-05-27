public class nibunn {
    public static void main(String[] args){
        int[] datas  = {4,5,2,6,20,17,21,3,11,9,18};

        for(int i =  0; datas.length-1 > i;i++){
            for(int x = 0;datas.length-1 > x;x++){
                if(datas[x] > datas[x+1]) {
                    int val;
                    val = datas[x];
                    datas[x] = datas[x+1];
                    datas[x+1] = val;
                }
            }
        }

        for (int data : datas) {
            System.out.print(data + ",");
        }
        serch serch = new serch();
       serch.serach(20,datas);


    }

}
