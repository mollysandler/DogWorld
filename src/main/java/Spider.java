import processing.core.PApplet;
import processing.core.PImage;


    public class Spider implements Draggable
//            , Cloneable
    {


        PImage img;
        protected PApplet screen;

        private int xPos, yPos, xHome, yHome;
        private int gridX = -1, gridY = -1;
        private final int width;
        private final int height;
        private boolean isDragging;
        private int xOffset;
        private int yOffset;
        private boolean visible = true;


        public Spider(PApplet screen, int xPos, int yPos, PImage img){
            this.screen = screen;
            this.xPos = xPos;
            this.yPos = yPos;
            xHome = xPos;
            yHome = yPos;
            this.img = img;
            width = img.width;
            height = img.height;

        }

        public void goHome() {
            xPos = xHome;
            yPos = yHome;
            gridX = -1;
            gridY = -1;
            visible = true;
        }

        //clone
//        public Diamond clone() throws CloneNotSupportedException{
//            return (Diamond) super.clone();
//        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }


        @Override
        public void display() {
            if ( visible ) screen.image(img, xPos, yPos);
        }


        @Override
        public boolean isMouseOver() {
            return (((xPos < screen.mouseX) && (screen.mouseX < xPos + width))
                    && ((yPos < screen.mouseY) && (screen.mouseY < yPos + height)));
        }


        @Override
        public void setxPos(int x) {
            this.xPos = x;
        }


        @Override
        public void setyPos(int y) {
            this.yPos = y;

        }

        public void setGridX(int gridX) {
            this.gridX = gridX;
        }

        public void setGridY(int gridY) {
            this.gridY = gridY;
        }

        public int getGridX() {
            return gridX;
        }

        public int getGridY() {
            return gridY;
        }

        public int getyPos() {
            return this.yPos;
        }
        public int getxPos() {
            return this.xPos;
        }

        public void setIsDragging(boolean newIsDragging) {
            this.isDragging = newIsDragging;
        }


        public void drag() {
            if (isDragging) {
                xPos = screen.mouseX - xOffset;
                yPos = screen.mouseY - yOffset;
            }
        }

        public void mousePressed() {
            if(isMouseOver()){ //calculate offset for the dragging
                xOffset = Math.abs(xPos - screen.mouseX);
                yOffset = Math.abs(yPos - screen.mouseY);
                isDragging = true;
            }
        }



        void snapToGrid(int gridX, int gridY, int cellSize) {
            setxPos(gridX * cellSize + 300);
            setyPos(gridY * cellSize + 250);
        }

        public void mouseReleased() {
            isDragging = false;
        }



    }


