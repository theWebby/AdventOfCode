import java.util.*;

class Map {
    public char[] CART_ICONS = new char[]{'>', '<', 'v', '^'};
    public Tile[][] map;
    public Cart[] carts;
    public boolean crash = false;
    
    public Map(char[][] rawMap){
        map = new Tile[rawMap.length][rawMap[0].length];
        processMap(rawMap);
        processCarts(rawMap);
    }
    
    public void advanceCarts(){
        for (Cart cart : carts){
            this.map = cart.advance(map);

            if (cart.hasCrashed){
                this.crash = true;
            }
        }
    }

    public void print(){
        for (Tile[] row : map){
            for (Tile t : row){
                t.print();
            }
            System.out.println();
        }
    }

    private void processMap(char[][] rawMap){
        boolean isCart;

        for (int y = 0; y < rawMap.length; y++){
            for (int x = 0; x < rawMap[y].length; x++){
                isCart = false;
                for (char icon : CART_ICONS){
                    if (rawMap[y][x] == icon){
                        isCart = true;
                        break;
                    }
                }

                if (isCart){
                    map[y][x] = processCartOnTile(x, y, rawMap[y][x]);
                }
                else{
                    map[y][x] = new Tile(rawMap[y][x]);
                }
            }   
        }
    }

    private Tile processCartOnTile(int x, int y, char cartIcon){
        switch (cartIcon){
            case '>':
                return new Tile('-', '>');
            case '<':
                return new Tile('-', '<');
            case '^':
                return new Tile('|', '^');
            case 'v':
                return new Tile('|', 'v');
        }
        return new Tile(' ');
    }

    private void processCarts(char[][] rawMap){
        ArrayList<Cart> cartList = new ArrayList<Cart>();

        for (int y = 0; y < rawMap.length; y++){
            for (int x = 0; x < rawMap[y].length; x++){
                for (char icon : CART_ICONS){
                    if (map[y][x].cartChar == icon){
                        cartList.add(new Cart(x, y, icon));
                        break;
                    }
                }
            }   
        }

        this.carts = cartList.toArray(new Cart[0]);
    }
}