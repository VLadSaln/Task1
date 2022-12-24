
import java.util.*;


public class URLPool {
    
    private int waitCount = 0;

    private int maxDepth;

    
    private LinkedList<URLDepthPair> toProcessURLs;

   
    private LinkedList<URLDepthPair> processedURLs;

   
    private HashSet<String> unrepeatedURLs;

    public URLPool(int _maxDepth) {
        toProcessURLs = new LinkedList<URLDepthPair>();
        processedURLs = new LinkedList<URLDepthPair>();
        unrepeatedURLs = new HashSet<String>();

        maxDepth = _maxDepth;
    }

    public int getWaitCount() {
        synchronized (this) {
            return waitCount;
        }
    }

    public URLDepthPair get() {
        synchronized (this) {
            while (toProcessURLs.size() == 0) {
                waitCount++;
                try {
                    wait();
                } catch (InterruptedException e) {
                }
                waitCount--;
            }

            return toProcessURLs.removeFirst();
        }
    }

    
    public void add(URLDepthPair nextPair) {
        synchronized (this) {
            String url = nextPair.getURL().toString();
            String cut = (url.endsWith("/")) ? url.substring(0, url.length() - 1) : url;
            if (unrepeatedURLs.contains(cut)) {
                return;
            }
            unrepeatedURLs.add(cut);

            if (nextPair.getDepth() < maxDepth) {
                toProcessURLs.add(nextPair);
                notify();
            }
            processedURLs.add(nextPair);
        }
    }

   
    public void printURLs() {
        synchronized (this) {
            while (!processedURLs.isEmpty()) {
                System.out.println(processedURLs.removeFirst());
            }
        }
    }
}