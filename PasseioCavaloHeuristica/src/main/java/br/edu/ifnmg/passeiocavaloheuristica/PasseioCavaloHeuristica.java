package br.edu.ifnmg.passeiocavaloheuristica;

/**
 *
 * @author Gustavo
 */
public class PasseioCavaloHeuristica {

    int[][] tabuleiro = new int[8][8];
    //Por não passar nenhum valor, em Java inicializar int como 0;
    
    int[][] accessibility = {{2, 3, 4, 4, 4, 4, 3, 2},
                             {3, 4, 6, 6, 6, 6, 4, 3},
                             {4, 6, 8, 8, 8, 8, 6, 4},
                             {4, 6, 8, 8, 8, 8, 6, 4},
                             {4, 6, 8, 8, 8, 8, 6, 4},
                             {4, 6, 8, 8, 8, 8, 6, 4},
                             {3, 4, 6, 6, 6, 6, 4, 3},
                             {2, 3, 4, 4, 4, 4, 3, 2}};
    
    int[] horizontal = {2,1,-1,-2,-2,-1,1,2};
    int[] vertical = {-1,-2,-2,-1,1,2,2,1};
    
    int currentRow, currentColumn, moveNumber;
    int contador; //Variavel para contar quantas casas o cavalo passou
    
    PasseioCavaloHeuristica(int x, int y){ //Construtor x=horizontal y=vertical
        this.currentColumn = y;
        this.currentRow = x;
        this.tabuleiro[x][y] = 1;
        //O valor inicial do tabuleiro já começa marcado.

        this.contador = 1;
    }
    
    /**
     * Metódo dinamico usado pra verificar se o cavalo pode ir para aquela casa
     * @return boolean 
     */
    private boolean validarCasa(){
        //Verifica se está dentro do tabuleiro
        if(currentColumn+vertical[moveNumber] > -1
           && currentRow+horizontal[moveNumber] > -1
           && currentColumn+vertical[moveNumber] < 8
           && currentRow+horizontal[moveNumber] < 8)
        {
            //vertifica se já passou pela casa
            if(tabuleiro
/*Linha*/     [currentRow+horizontal[moveNumber]]
/*Coluna*/    [currentColumn+vertical[moveNumber]]==0)
            {
                return true;
            }   
        }
        return false;
    }
    /**
     * Metodo para diminuir a acessibilidade das casas em volta da casa
     * selecionada.
     */
    private void diminuirAcessibilidade(){   
        for(int j = 0; j < 8; j++){
            //Verifica se está dentro do tabuleiro
            if(currentColumn+vertical[j] > -1
               && currentRow+horizontal[j] > -1
               && currentColumn+vertical[j] < 8
               && currentRow+horizontal[j] < 8)
            {
                accessibility
/*Linha*/           [currentRow+horizontal[j]]
/*Coluna*/          [currentColumn+vertical[j]]-=1;
            }
        }
    }
    
    /**
    *Método dinamico para verificar o melhor local do cavalo baseado no
     tabuleiro de acessibilidade.
    */
    private void verificarAcesso(){
        int prioridade = 9;
        //Como ele vai pelo menor numero de acessibilidade, add um valor maior
        
        int x = currentRow;
        int y = currentColumn;
        for(moveNumber = 0; moveNumber < 8; moveNumber++){
            if(validarCasa()){
                //IF abaixo verifica a casa q tem menor acessibilidade no tabuleiro
                if(accessibility
/*Linha*/             [currentRow+horizontal[moveNumber]]
/*Coluna*/            [currentColumn+vertical[moveNumber]]<prioridade)
                {
                    prioridade = accessibility
/*Linha*/               [currentRow+horizontal[moveNumber]]
/*Coluna*/              [currentColumn+vertical[moveNumber]];
                    x = currentRow+horizontal[moveNumber];
                    y = currentColumn+vertical[moveNumber];
                    //Salvamos em variaveis, para não alterar outras verificações
                }
            }
        }
        currentRow = x;
        currentColumn = y;
        contador+=1;
        tabuleiro[currentRow][currentColumn] = contador;
        diminuirAcessibilidade();
    }
    
    /**
     * Método para printar o tabuleiro com os movimentos que o cavalo fez
     * Primeiro movimento igual 1, segundo 2 e assim por diante..
     */
    public void printarTabuleiro(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                System.out.printf("[%2d]", tabuleiro[i][j]);
            }
            System.out.println("");
        }
    }

    /**
     * método dinamico para movimentar o cavalo pelo tabuleiro.
     */
    public void movimentarCavalo( ){
        for(moveNumber = 0; moveNumber < 8; moveNumber++){
            if(validarCasa()){
                verificarAcesso();
                moveNumber = -1;  
            }   
        }
        printarTabuleiro();
        System.out.println("Quantidade de casas visitdas: "+contador);
    }
}
    

