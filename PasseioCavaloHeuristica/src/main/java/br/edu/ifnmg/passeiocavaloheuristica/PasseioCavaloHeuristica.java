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
    
    public void Acessibilidade(){
        int prioridade = 9;
        int x = currentRow;
        int y = currentColumn;
        //Como ele vai pelo menor numero de acessibilidade, add um valor maior
        for(int i = 0; i < 8; i++){
            //Verifica se está dentro do tabuleiro
            if(currentColumn+vertical[i] > -1
               && currentRow+horizontal[i] > -1
               && currentColumn+vertical[i] < 8
               && currentRow+horizontal[i] < 8)
            {
                //vertifica se já passou pela casa
                if(tabuleiro
/*Linha*/         [currentRow+horizontal[i]]
/*Coluna*/        [currentColumn+vertical[i]]!=1)
                {
                    if(accessibility
/*Linha*/             [currentRow+horizontal[i]]
/*Coluna*/            [currentColumn+vertical[i]]<prioridade)
                    {
                        
                        prioridade = accessibility
/*Linha*/               [currentRow+horizontal[i]]
/*Coluna*/              [currentColumn+vertical[i]];
                        x = currentRow+horizontal[i];
                        y = currentColumn+vertical[i];
                        //Salvamos em variaveis, para não alterar outras verificações
                    }
                }
            }
        }
        currentRow = x;
        currentColumn = y;
        tabuleiro[currentRow][currentColumn] = 1;
        System.out.println("["+currentRow+"]["+currentColumn+"]");
        contador+=1;
        
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
    
    public void movimentarCavalo( ){
        System.out.println("["+currentRow+"]["+currentColumn+"]");
        for(moveNumber = 0; moveNumber < 8; moveNumber++){
            //Verifica se está dentro do tabuleiro
            if(currentColumn+vertical[moveNumber] > -1
               && currentRow+horizontal[moveNumber] > -1
               && currentColumn+vertical[moveNumber] < 8
               && currentRow+horizontal[moveNumber] < 8)
            {
                //vertifica se já passou pela casa
                if(tabuleiro
/*Linha*/         [currentRow+horizontal[moveNumber]]
/*Coluna*/        [currentColumn+vertical[moveNumber]]!=1)
                {
                    Acessibilidade();
                    moveNumber = -1;  
                }
                
            }
        }
        
        System.out.println(contador);
    }
    
}
