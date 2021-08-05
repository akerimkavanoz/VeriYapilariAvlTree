public class AVLTree 
{
    Node root; 
  
    int height( Node N )           // Ağacın yükseliğini bulmak için yardımcı metot
    {    
        if ( N == null )
        {
            return 0;              // Ağacın içi boş (null) ise 0 döndürür
        }
        
        return N.height;           // Ağacın içi dolu ise yükseklik değerini döndürür
    } 
  
    int max( int a, int b )        // Hangi sayının büyük olduğunu bulur. Bu metot sol ve sağ dallardan hangi dalın yüksek olduğunu bulurken kullanılır   
    {    
        if ( a > b )
        {
            return a;              // a, b'den büyük ise a'yı döndürür
        }
        
        else                       
        { 
            return b;              // b, a'dan büyük ise b'yi döndürür
        }
    } 
 
    Node rightRotate( Node y )     // Ağaçtaki dengeyi düzeltirken alt ağacı sağa döndüren metot
    {    
        Node x = y.left;  
        Node T2 = x.right; 
       
        x.right = y;               // Rotasyonu ayarlar
        y.left = T2;               // Rotasyonu ayarlar
   
        y.height = max( height(y.left), height(y.right) ) + 1;     // Y nin yükseklik değerini güncellenir
        x.height = max( height(x.left), height(x.right) ) + 1;     // X in yükseklik değerini güncellenir
  
        return x;                  // Yeni kökü döndürür
    } 
   
    Node leftRotate( Node x )      // Ağaçtaki dengeyi düzeltirken alt ağacı sola döndüren metot
    {   
        Node y = x.right; 
        Node T2 = y.left; 
  
        y.left = x;                // Rotasyonu ayarlar
        x.right = T2;              // Rotasyonu ayarlar
  
        x.height = max( height(x.left), height(x.right) ) + 1;     // X in yükseklik değeri güncellenir
        y.height = max( height(y.left), height(y.right) ) + 1;     // Y nin yükseklik değeri güncellenir
  
        return y;                  // Yeni kökü döndürür
    } 
  
    int getBalance( Node N )       // Düğümün dengesini konrtol etmek için yardımcı metot
    {    
        if ( N == null ) 
        {
            return 0;              // Ağacın içi boş (null) ise yükseklik değerini 0 döndürür
        }
            
        return height(N.left) - height(N.right);      // Düğümün sol yükseklik değerinden, sağ yükseklik değerini çıkarır ve değeri döndürür
    } 
    
    // **************************** ekleme **********************
    
    Node insert( Node node, String key )              // Ağaca veri ekleme metodu
    { 
        if ( node == null )                           // Ağaç boş ise, girilen veri kök düğüm olur
        {
            return ( new Node(key) ); 
        }
        
        if ( key.compareTo(node.key) < 0 )            // Girilen veri ağaçtaki veriden küçük ise ağacın sol dalına eklenir
        {   
            node.left = insert( node.left, key );  
        }
        
        else if ( key.compareTo( node.key) > 0 )      // Girilen veri ağaçtaki veriden büyük ise ağacın sağ dalına eklenir
        {    
            node.right = insert( node.right, key ); 
        }
        
        else                                          // Aynı veri tekrar ağaca eklenmez 
        {    
            return node; 
        }
        
        // Ekleme işleminden sonra denge kontrol edilir
        
        node.height = 1 + max( height(node.left), height(node.right) );   // Düğümün yüksekliği güncellenir
  
        int balance = getBalance(node);                                     
  
        // Ağaç dengede değil ise 4 durum oluşur
        
        // Left left durumu
        if ( balance > 1 && key.compareTo( node.left.key ) < 0 )           
        {   
            return rightRotate(node); 
        }
        
        // Right Right durumu 
        if ( balance < -1 && key.compareTo( node.right.key ) > 0 )        
        {
            return leftRotate(node); 
        }
        
        // Left Right durumu 
        if ( balance > 1 && key.compareTo( node.left.key ) > 0 )          
        {                                                                 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // Right Left durumu 
        if ( balance < -1 && key.compareTo( node.right.key ) < 0 ) 
        { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        return node; 
    } 
  
    // **************************** silme **********************
    
    Node minValueNode( Node node )  
    {  
        Node current = node;  
  
        while ( current.left != null )              // Left değeri null olana kadar döner ve her seferinde current değeri current.lefte eşitlenir
        {
            current = current.left;                 // Döngü bitince en soldaki yaprağa ulaşılmış olur
        }
        
        return current;                             // Current değeri döndürülür
    }  
  
    Node deleteNode( Node root, String key )        // Ağaçtan veri silme metodu
    {  
        if ( root == null )                                        
        {   
            return root;  
        }

        if ( key.compareTo( root.key ) < 0 )        // Silinecek veri ağaçtaki veriden küçük ise sol dal ve veri metoda tekrar yollanır. İşleme sol daldan devam edilir
        {    
            root.left = deleteNode( root.left, key );  
        }
        
        else if ( key.compareTo( root.key ) > 0 )   // Silinecek veri ağaçtaki veriden büyük ise sağ dal ve veri metoda tekrar yollanır. İşleme sağ daldan devam edilir
        {
            root.right = deleteNode( root.right, key );  
        }

        else                                       // Ağaçtaki veri ile aranan veri eşit ise silme işlemi yapılır
        {   
            if ( ( root.left == null ) || ( root.right == null ) )   // Sağ veya sol dallardan herhangi birisinin boş olduğu durum
            {  
                Node temp = null;                  // Temp değeri oluşturulur ve null değeri verilir 
                
                if ( temp == root.left )           // Temp, root.lefte eşit ise temp değeri root.right yapılır 
                {    
                    temp = root.right;             
                }
                
                else                               // Temp, root.lefte eşit değil ise temp değeri root.left yapılır 
                {
                    temp = root.left;  
                }
                 
                if (temp == null)                  // Temp değeri null ise temp e root değeri atanır ve root değeri null yapılır
                {  
                    temp = root;  
                    root = null;  
                }  
                
                else                               // Temp değeri null değil ise root, tempe eşitlenir
                {
                    root = temp;                   
                }                
            }  
            
            else                                   // Hem sağ hem sol dalda veri olduğu durum
            {   
                  
                Node temp = minValueNode(root.right);               // Temp içine sağ daldaki en küçük değer atılır               
                root.key = temp.key;                                // root.key , temp.key e eşitlenir
                root.right = deleteNode(root.right, temp.key);      // Silme işlemi yapılır
            }  
        }  
  
        if ( root == null )  
        { 
            return root;  
        }
        
        // Silme işleminden sonra denge kontrol edilir
        
        root.height = max( height(root.left), height(root.right) ) + 1;         // Güncel düğümün yükseklik değeri güncellenir
  
        int balance = getBalance(root);  
  
        // Ağaç dengede değil ise 4 durum oluşur
        
        // Left Left durumu  
        if ( balance > 1 && getBalance( root.left ) >= 0 )  
        { 
            return rightRotate(root);  
        }
        // Left Right durumu  
        if ( balance > 1 && getBalance( root.left ) < 0 )  
        {  
            root.left = leftRotate( root.left );  
            return rightRotate(root);  
        }  
  
        // Right Right durumu  
        if ( balance < -1 && getBalance( root.right ) <= 0 )  
        {
            return leftRotate(root);  
        }
        // Right Left durumu  
        if ( balance < -1 && getBalance( root.right ) > 0 )  
        {  
            root.right = rightRotate( root.right );  
            return leftRotate(root);  
        }  
  
        return root;  
    }  
    
    // **************************** ata **********************
    
    boolean printAncestors( Node node, String target )                // Ata metodu
    { 
        if ( node == null )                                           // Ağaç boş ise false değer döndürür
            return false; 
   
        if ( target.compareTo(node.key ) == 0 )                       // Aranan veri node.key e eşit ise true değer döndürür
            return true; 

        if ( printAncestors( node.left, target ) || printAncestors( node.right, target ) )  // Aranan değer sol veya sağ dalda var ise düğümü yazdırır ve true değer döndürür
        { 
            System.out.print(node.key + " "); 
            return true; 
        } 
   
        return false; 
    }  
    
    // **************************** arama **********************
     
    boolean ara( Node node, String key )                 // Ağaçta veri arama metodu
    {
        if( node == null )                               // Ağacın boş ise false değer döndürür
        {
            return false;
        }
        
        else if( key.compareTo(node.key) == 0 )          // Aranan veri ağaçtaki veriye eşit ise true değer döndürür
        {
            return true;
        }
        
        else if( key.compareTo(node.key) < 0 )           // Aranan veri ağaçtaki veriden küçük ise metoda ağacın sol dalı ve aranan veri tekrar yolanır. İşleme sol daldan devam edilir
        {
            return ara( node.left, key );
        }
        
        else
        {
            return ara( node.right, key );               // Aranan veri ağaçtaki veriden büyük ise metoda ağacın sağ dalı ve aranan veri tekrar yollanır. İşleme sağ daldan devam edilir
        }
        
    }
    
    boolean arama( String key )                          // Kök ve aranan veriyi ara metoduna yollar
    {
        return ara( root, key );                         
    }
    
    // **************************** yazdırma **********************
/*   
    void preOrder( Node node )                           // Bu metot programı denemek için yazılmıştır
    {  
        if ( node != null )  
        {  
            System.out.print(node.key + " ");  
            preOrder(node.left);  
            preOrder(node.right);  
        }  
    }
*/
}