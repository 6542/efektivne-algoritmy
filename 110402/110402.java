/*
    110402 - Stacks of Flapjacks
    ----------------------------

    Nacítam si riadok intov, ktoré si zapíšem do dvoch rôznych array listov v sortedFlapjacks ich natvrdo
    zoradím a porovnávam s ním pôvodne pole flapjacks aby som zistil ci je zoradené. Porovnáva ci je
    na spodku najväcšie císlo. Vezme si dlzku pôvodného pola a odráta od neho pocet prvkov v poli ktoré
    netreba otáca a zvyšok pola otocís. Takto to pokracuje až kým nie je zoradené.
*/

public class Main {
    public static void main(String[] args) {
        Main.begin();
    }

    private static void begin() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            processFlapjacks(sc.nextLine());
        }
    }

    private static void processFlapjacks(String line) {
        List<Integer> flapjacks = new ArrayList<Integer>();
        List<Integer> sortedFlapjacks = new ArrayList<Integer>();
        List<Integer> flipIndices = new LinkedList<Integer>();

        // Read in the flapjacks
        Scanner sc = new Scanner(line);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            flapjacks.add(n);
            sortedFlapjacks.add(n);
        }
        Collections.sort(sortedFlapjacks);

        int length = flapjacks.size();
        for (int i = length - 1; i > 0; i--) {
            int n = sortedFlapjacks.get(i);
            if (n == flapjacks.get(i)) {
                continue;
            }

            int flipIndex = length - flapjacks.indexOf(n);
            if (flipIndex != length) {
                flip(flapjacks, flipIndex);
                flipIndices.add(flipIndex);
            }

            flipIndex = length - i;
            flip(flapjacks, flipIndex);
            flipIndices.add(flipIndex);
        }

        // Print the original stack.
        System.out.println(line);
        print(flipIndices);
    }

    private static void print(List<Integer> flipIndices) {
        StringBuilder sb = new StringBuilder();
        for (Integer flipIndex : flipIndices) {
            sb.append(flipIndex);
            sb.append(" ");
        }
        sb.append(0);
        System.out.println(sb);
    }

    private static void flip(List<Integer> flapjacks, int flipIndex) {
        Collections.reverse(flapjacks.subList(0, flapjacks.size() - flipIndex + 1));
    }
}