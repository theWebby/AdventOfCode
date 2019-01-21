class Tile {
    char trackChar, cartChar = ' ';

    public Tile(char trackChar){
        this.trackChar = trackChar;
    }

    public Tile(char trackChar, char cartChar){
        this.trackChar = trackChar;
        this.cartChar = cartChar;
    }

    public void print(){
        if (cartChar == ' '){
            System.out.print(trackChar);
        }
        else{
            System.out.print(cartChar);
        }
    }
}