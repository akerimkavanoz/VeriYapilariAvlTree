import java.util.*;

public class main {
     
    public static void main(String[] args) { 
        
        Scanner reader = new Scanner(System.in,"ISO-8859-9");
 
        AVLTree tree = new AVLTree();  
        
        System.out.println("*******************************************************");
        System.out.println("                      İŞLEMLER                         ");
        System.out.println("ekle: AVL ağacına veri ekler\n"
                         + "sil : AVL ağacından veri siler\n"
                         + "ata : Girilen düğümün ebeveynini ve atalarını ekrana yazdırır\n"
                         + "  q : Programdan çıkış yapar ");
        System.out.println("*******************************************************");
       
        while( true )
        {
            System.out.print("Girdi: "); 
            String entry = reader.nextLine();
            entry = entry.toLowerCase();
          
            if ( entry.equals("ekle") )
            {
                System.out.print("Eklenecek veriyi giriniz: ");  
                String entry2 = reader.nextLine();
                entry2 = entry2.toLowerCase();
                
                if( entry2.isEmpty() )
                {
                    System.out.println("Ağaca boş veri eklenemez!\n");  
                }
                
                else
                {
                    if ( !(tree.arama(entry2)) )
                    {
                        tree.root = tree.insert(tree.root, entry2);
                        System.out.println("Ekleme işlemi başarı ile tamamlanmıştır.\n"); 
                        //System.out.print("Ağaç: ");  
                        //tree.preOrder(tree.root);        // Program denenmek için yazılmıştır
                        //System.out.println("\n");
                    }
                    
                    else
                    {
                        System.out.println("Bu veri zaten ağaçta mevcut!\n");
                    } 
                }     
            }

            else if ( entry.equals("sil") )
            {
                if ( tree.root != null )
                {  
                    System.out.print("Silinecek veriyi giriniz: ");  
                    String entry2 = reader.nextLine();
                    entry2 = entry2.toLowerCase();
                    
                    if( tree.arama(entry2) )
                    {
                        tree.root = tree.deleteNode(tree.root, entry2);  
                        System.out.println("Silme işlemi başarı ile tamamlanmıştır.\n");
                        //System.out.print("Ağaç: ");  
                        //tree.preOrder(tree.root);       // Program denenmek için yazılmıştır
                        //System.out.println("\n");
                    }
                    
                    else if( entry2.isEmpty() )
                    {
                        System.out.println("Boş veri ağaçtan silinemez!\n");
                    }
                    
                    else
                    {
                        System.out.println("Bu veri ağaçta mevcut değil!\n");
                    }   
                }
                
                else
                {
                    System.out.println("Ağaçta silinecek veri yok!\n");
                }     
            }
            
            else if ( entry.equals("ata") )
            { 
                if( tree.root != null )
                {
                    System.out.print("Ata düğümlerini aratacağınız düğümü giriniz: ");  
                    String entry2 = reader.nextLine();
                    entry2 = entry2.toLowerCase();
                    
                    if( tree.root.key.equals(entry2) )
                    {
                        System.out.println("Aradığınız düğüm kök düğümdür.\n");
                    }
                    
                    else if ( entry2.isEmpty() )
                    {
                        System.out.println("Boş veri aratılamaz!\n");
                    }
                    
                    else if ( !(tree.arama(entry2)) )
                    {
                        System.out.println("Aradığınız düğüm ağaçta mevcut değil!\n");
                    }

                    else
                    {
                        tree.printAncestors(tree.root, entry2); 
                        System.out.println("\n");
                    }
                }
                
                else
                {
                    System.out.println("Ağaçta aranacak veri yok!\n");
                }
                  
            }

            else if( entry.equals("q") )
            {
                break;
            }

            else
            {
                System.out.println("Yanlış işlem!\n");
            }
        }      
    }    
}
