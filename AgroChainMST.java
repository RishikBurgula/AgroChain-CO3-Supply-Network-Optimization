import java.util.*;

class Edge implements Comparable<Edge> {
    String source, destination;
    int cost;

    Edge(String source, String destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}

public class AgroChainMST {

    static Map<String, String> parent = new HashMap<>();

    static String find(String node) {
        if (parent.get(node).equals(node))
            return node;

        String root = find(parent.get(node));
        parent.put(node, root);
        return root;
    }

    static void union(String a, String b) {
        parent.put(find(a), find(b));
    }

    public static void main(String[] args) {

        String[] locations = {
                "Farm A",
                "Warehouse B",
                "Market C",
                "Warehouse D",
                "Retail E"
        };

        for (String location : locations)
            parent.put(location, location);

        ArrayList<Edge> routes = new ArrayList<>();

        routes.add(new Edge("Farm A", "Warehouse B", 10));
        routes.add(new Edge("Farm A", "Market C", 15));
        routes.add(new Edge("Warehouse B", "Warehouse D", 20));
        routes.add(new Edge("Market C", "Warehouse D", 5));
        routes.add(new Edge("Warehouse D", "Retail E", 12));
        routes.add(new Edge("Market C", "Retail E", 18));

        System.out.println("========================================");
        System.out.println(" AGROCHAIN SUPPLY NETWORK OPTIMIZATION ");
        System.out.println("========================================");

        System.out.println("\nAvailable Transportation Routes:");

        for (Edge e : routes) {
            System.out.println(
                    e.source + " <--> " +
                    e.destination + " : ₹" +
                    e.cost + "/km");
        }

        Collections.sort(routes);

        int totalCost = 0;

        System.out.println("\nSelected MST Routes:");

        for (Edge e : routes) {

            String root1 = find(e.source);
            String root2 = find(e.destination);

            if (!root1.equals(root2)) {

                union(root1, root2);

                System.out.println(
                        e.source + " <--> " +
                        e.destination + " : ₹" +
                        e.cost + "/km");

                totalCost += e.cost;
            }
        }

        System.out.println("\n----------------------------------------");
        System.out.println("Minimum Transportation Cost = ₹" +
                totalCost + "/km");
        System.out.println("----------------------------------------");

        System.out.println("\nNetwork Connected Successfully");
        System.out.println("Supply Chain Optimized Using Kruskal MST");
    }
}
