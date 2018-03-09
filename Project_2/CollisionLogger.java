/* Class called CollisionLogger */

public class CollisionLogger{

    /* Data members of the Class CollisionLogger */

    private int width, height, bucketW;
    int[][] bucketHolder;
    private int tempi, tempj;
    private final int BORDER = 30;

    /* Constructor for the Class CollisionLogger */

    public CollisionLogger(int screenWidth, int screenHeight, int bucketWidth) {

        width = screenWidth;
        height = screenHeight;
        bucketW = bucketWidth;
        bucketHolder = new int[width/bucketW][height/bucketW];

    }

     /*
     * This method records the collision event between two balls. Specifically, in increments the bucket corresponding to
     * their x and y positions to reflect that a collision occurred in that bucket.
     */

    public void collide(Ball one, Ball two) {
    	/* update data members to reflect the collision event between ball "one" and ball "two" */

        if (one.intersect(two)) {


            for (int i =0; i<(width/bucketW);i++){ /* x co-ordinate of bucket of collision between ball1 and ball2 */
                if ((i*bucketW) < ((one.getXPos()+ two.getXPos())/2) && ((one.getXPos()+ two.getXPos())/2) <= ((i+1)*bucketW)){
                    tempi= i;
                }
            }

            for (int j =0; j<(height/bucketW);j++){ /* y co-ordinate of bucket of collision between ball1 and ball2 */
                if ((j*bucketW) < ((one.getYPos()+ two.getYPos())/2) && ((one.getYPos()+ two.getYPos())/2) <= ((j+1)*bucketW)){
                    tempj= j;
                }
            }

            bucketHolder[tempi][tempj] = bucketHolder[tempi][tempj] + 1; /* updating the counter for collision between ball1 and ball2 */

        }
        getNormalizedHeatMap();

    }

    /*
     * Returns the heat map scaled such that the bucket with the largest number of collisions has value 255,
     * and buckets without any collisions have value 0.
     */

    public int[][] getNormalizedHeatMap() {

        int[][] normalizedHeatMap = new int[width/bucketW][height/bucketW];

         /* NOTE-- these dimensions need to be updated properly!! */
    	/* implement your code to produce a normalized heat map of type int[][] here */

    	/* Finding the maximum of number of collisions from bucketHolder Matrix */

        int max = 1;
        for (int i = 0; i < width/bucketW; i++){
            for (int j = 0; j < height/bucketW; j++){
                if (max < bucketHolder[i][j]){
                    max = bucketHolder[i][j];
                }
            }
        }

        /* Transforming or re-scaling the bucketHolder matrix to a normalizedHeatMap */

        for (int i = 0; i < width/bucketW; i++){
            for (int j = 0; j < height/bucketW; j++){
                int value = bucketHolder[i][j];
                normalizedHeatMap[i][j] = (value * 255) / max;
            }
        }

        return normalizedHeatMap;
    }
}
