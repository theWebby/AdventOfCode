class Cart {
    public String[] actions = new String[]{"left", "straight", "right"};
    public char icon;
    public int x, y, nextAction = 0;
    public boolean hasCrashed = false;

    public Cart(int x, int y, char icon){
        this.x = x;
        this.y = y;
        this.icon = icon;
    }


    public Tile[][] advance(Tile[][] map){
        switch (icon){
            case '>': //right
                map = moveDir(x + 1, y, map);
                break;
            case '<': //left
                map = moveDir(x - 1, y, map);
                break;
            case 'v': //down
                map = moveDir(x, y + 1, map);
                break;
            case '^': //up
                map = moveDir(x, y - 1, map);
                break;    
        }
        return map;
    }

    //this should have worked....
    private Tile[][] moveDir(int newX, int newY, Tile[][] map){
        
        
        char newCartChar = getNewCartChar(newX, newY, map[newY][newX]);
     
        if (newCartChar == 'X'){
            System.out.println("Crash");
            System.out.println(newX);
            System.out.println(newY);
        
            this.hasCrashed = true;
        }
        

        map = move(newX, newY, newCartChar, map);
        return map;
    }

    boolean moveVertical;;
    private char getNewCartChar(int newX, int newY, Tile nextTile){
        moveVertical = (this.y != newY) ? true : false;

        if (nextTile.cartChar != ' '){
            return 'X';
        }

        switch (nextTile.trackChar){
            case '-':
                return ((this.x - newX) > 0) ? '<' : '>';
            case '|':
                return ((this.y - newY) > 0) ? '^' : 'v';
            case '/':
                if (moveVertical){
                    return ((this.y - newY) > 0) ? '>' : '<';
                } else {
                    return ((this.x - newX) > 0) ? 'v' : '^';
                }
                
            case '\\':
                if (moveVertical){
                    return ((this.y - newY) > 0) ? '<' : '>';
                } else {
                    return ((this.x - newX) > 0) ? '^' : 'v';
                }
            case '+':
                this.nextAction = (this.nextAction >=3) ? 1 : this.nextAction + 1;
                if (moveVertical){ 
                    switch(this.nextAction - 1){
                        case 0:
                            return ((this.y - newY) > 0) ? '<' : '>';
                        case 1:
                            return ((this.y - newY) > 0) ? '^' : 'v';
                        case 2:
                            return ((this.y - newY) > 0) ? '>' : '<';
                        
                    }
                }
                else{
                    switch(this.nextAction - 1){
                        case 0:
                            return ((this.x - newX) > 0) ? 'v' : '^';
                        case 1:
                            return ((this.x - newX) > 0) ? '<' : '>';
                        case 2:
                            return ((this.x - newX) > 0) ? '^' : 'v';
                        
                    }   
                }
            default:
                System.out.println("Cart vanished");
                return ' '; //error
        }
    }

    private Tile[][] move(int newX, int newY, char newCartChar, Tile[][] map){ 
        map[newY][newX].cartChar = newCartChar;
        map[y][x].cartChar = ' ';
        this.x = newX;
        this.y = newY;
        this.icon = newCartChar;
        return map;
    }
}
