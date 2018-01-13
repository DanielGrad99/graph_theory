import java.util.*;

public class FindPath {

    static Scanner in = new Scanner(System.in);

    static ArrayList<Node> Graph;
    static String Dest;

    public static void main(String[] args){

        int NumberOfNodes = in.nextInt();
        Graph = new ArrayList<Node>();

        for (int count = 0; count < NumberOfNodes; count++){

            Graph.add(new Node(in.next(), new ArrayList<String>()));

            while(!in.hasNext("\\.")){
                Graph.get(count).Neighbours.add(in.next());
            }

            in.next();
        }


        String Start =  in.next();
        Dest = in.next();

        ArrayList<String> Path = FindPathNode(Start);
        int length = Path.size();

        if (Path.isEmpty())
            System.out.println("No possible route.");

        else
            for (int count = 0; count < length; count++){
                System.out.print(Path.get(length - 1 - count));
                if (count<length-1) System.out.print("->");
            }

    }


    private static ArrayList<String> Neighbours(String node){

        for (int count = 0; count < Graph.size(); count++){
            if (node.equals(Graph.get(count).Name)){
                if (Graph.get(count).Visited) return new ArrayList<String>();

                Graph.get(count).Visited = true;
                return Graph.get(count).Neighbours;
            }
        }

        return new ArrayList<String>();
    }


    private static ArrayList<String> FindPathNode(String Start){

        if (Start.equals(Dest)){
            return new ArrayList<String>(Arrays.asList(Start));
        }

        ArrayList<String> Path = FindPathList(Neighbours(Start));

        if (Path.isEmpty()) return new ArrayList<String>();

        Path.add(Start);
        return Path;
    }


    private static ArrayList<String> FindPathList(ArrayList<String> Nbrs){

        if (Nbrs.isEmpty()) return new ArrayList<String>();
        ArrayList<String> Path = FindPathNode(Nbrs.get(0));

        if (Path.isEmpty()){
            Nbrs.remove(0);
            return FindPathList(Nbrs);
        }

        return Path;
    }
}

class Node {

    public String Name;
    public ArrayList<String> Neighbours;
    public Boolean Visited;

    public Node(String Name, ArrayList<String> Neighbours){
        this.Name = Name;
        this.Neighbours = Neighbours;
        Visited = false;
    }
}
