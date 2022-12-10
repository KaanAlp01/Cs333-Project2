import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KAAN_ALP_S018037 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the number of Outlets: ");
        int outletNumber = Integer.parseInt(scan.nextLine());
        String[] outlets = new String[outletNumber];
        String[] lamps = new String[outletNumber];
        System.out.println("Please enter the order of outlets with a space in between: ");
        for (int i = 0; i < outletNumber; i++)
            outlets[i] = scan.next();
        System.out.println("Please enter the order of lamps with a space in between: ");
        for (int i = 0; i < outletNumber; i++)
            lamps[i] = scan.next();
        checkConnection(transformArrayInt(lamps, outlets), lamps);
    }

    public static void checkConnection(int[] outlets, String[] lamps) {
        if (outlets.length == 0) {
            System.out.println("You haven't given any outlets try again.");
            return;
        }
        List<List<Integer>> subSequences = new ArrayList<>();
        for (int i = 0; i < outlets.length; i++) {
            subSequences.add(new ArrayList<>());
        }
        subSequences.get(0).add(outlets[0]);
        for (int i = 1; i < outlets.length; i++) {
            for (int j = 0; j < i; j++) {
                if (subSequences.get(j).size() > subSequences.get(i).size() && outlets[j] < outlets[i])
                    subSequences.set(i, new ArrayList<>(subSequences.get(j)));
            }
            subSequences.get(i).add(outlets[i]);
        }
        int index = 0;
        for (int i = 0; i < outlets.length; i++) {
            if (subSequences.get(i).size() > subSequences.get(index).size())
                index = i;
        }
        String[] temp = transformArrayString(lamps, subSequences.get(index));
        System.out.println(temp.length);
        for (String s : temp) System.out.print(s + " ");
    }

    public static int[] transformArrayInt(String[] lamps, String[] outlets) {
        int[] intOutlets = new int[outlets.length];
        for (int i = 0; i < outlets.length; i++) {
            for (int j = 0; j < lamps.length; j++) {
                if (lamps[j].equals(outlets[i]))
                    intOutlets[i] = j;
            }
        }
        return intOutlets;
    }

    public static String[] transformArrayString(String[] lamps, List<Integer> index) {
        String[] stringOutlets = new String[index.size()];
        for (int i = 0; i < index.size(); i++) {
            stringOutlets[i] = lamps[index.get(i)];
        }
        return stringOutlets;
    }
}
