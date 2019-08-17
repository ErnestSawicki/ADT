package pl.sda.List;

public class MainList {

    public static void main(String[] args) {
        IList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);


        Integer[] tab = list.getHolderView();

        for (long val:tab)
            System.out.print(val + " ");
    }
}
