import java.io.*;

/**
 * This class stores braille characters in a binary tree.
 * @author Catie Baker
 *
 */
public class BrailleTree {
    BrailleNode root;

    /**
     * Creates the Braille tree from the alphabet in the provided file.
     * The file should be formated, one braille character per line, with
     * first the braille encoding as a series of six bits representing the
     * six dots in a top to bottom, left to right order. Then after the encoding
     * there will be a space followed by the text that braille character
     * represents. In addition, it adds the all 0 encoding (000000 for six dot braille)
     * as a space.
     * @param filename the name of the file that stores the encoding mapping
     */
    public BrailleTree(String filename) {
        //declaring root of the braille binary tree
        root=new BrailleNode("");
        File file = new File(filename);
        BufferedReader br= null;
        try {
            br = new BufferedReader(new FileReader(file));
            String s;
            while ((s = br.readLine()) != null)
            {
                int ind=s.indexOf(' ');
                add(s.substring(0,ind),s.substring(ind+1));
            }
            add("000000"," ");
        } catch (FileNotFoundException e) {
            System.out.println("File not found at the specified location error>"+e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to read the file error>"+e.getMessage());
        }
    }

    /**
     * Adds the braille character with the provided binary
     * encoding to the tree with the provided text. If there is
     * already something with that encoding in the tree, it replaces
     * that text.
     * @param binary the braille encoding of the character
     * @param text the text that the character represents
     */
    public void add(String binary, String text) {
        BrailleNode curr=root;
        for(int i=0;i<binary.length();i++)
        {
            //inserting 1 to the right of the current node
            if(binary.charAt(i)=='1')
            {
                if (curr.getRight() == null) {
                    curr.setRight(new BrailleNode(""));
                }
                curr=curr.getRight();
            }
            else    //inserting 0 to the left of the current node
            {
                if (curr.getLeft() == null) {
                    curr.setLeft(new BrailleNode(""));
                }
                curr=curr.getLeft();
            }
            if(i==binary.length()-1)
            {
                curr.setText(text);
            }
        }
    }


    /**
     * Gets the text associated with the provided braille character
     * @param binary the binary encoding of the braille character
     * @return the text associated with the braille character or
     * the empty string if there is no such encoding in the tree
     */
    public String getText(String binary) {
        BrailleNode curr=root;
        for(int i=0;i<binary.length();i++)
        {
            //if the encoding is 1 search in the right subtree
            if(binary.charAt(i)=='1')
            {
                curr=curr.getRight();
            }
            else    //if the encoding is 0 search in the left subtree
            {
                curr=curr.getLeft();
            }
        }
        return curr.getText();
    }


    /**
     * Finds and returns the braille encoding for the provided text
     * @param text the text to find the braille encoding of
     * @return the binary braille encoding that has that text or
     * the empty string if that text is not in the tree.
     */
    public String getBraille(String text) {
        //doing an inorder traversal of the encoded braille tree to get the path for the specified text

        String braille= inorder(root,"",text);
        if(braille.isEmpty())
        {
            return "The text isn't encoded in the Braille tree.";
        }
        return braille;
    }

    String inorder(BrailleNode node,String path,String text)
    {
        if (node == null)
            return "";
        if(text.equals(node.getText()))
            return path;

        String path1=inorder(node.getLeft(),path+"0",text);
        String path2=inorder(node.getRight(),path+"1",text);

        //if path1 return non-empty string, then the text is in the left subtree
        //if path2 return non-empty string, then the text is in the right subtree
        //if path1 and path2 return empty string, then the text is not present in the braille tree

        if(path1=="" && path2=="")
            return "";
        else if(path1!="")
            return path1;
        else
            return path2;
    }


    /**
     * Given a file written in braille, it translates it to the
     * text provided in the tree and writes it to a new file, outfile.
     * @param infile the braille file to translate
     * @param outfile the file to write the translation to
     */
    public void translateFile(String infile, String outfile) {
        File file = new File(
                infile);

        String r=getBraille("w");
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        // Creating an object of BufferedReader class
        BufferedReader br
                = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String s;
            while ((s = br.readLine()) != null)
            {
                String text="";
                for(int i=0;i<s.length();i+=6)
                {
                    String braille=s.substring(i,i+6);
                    text=text+getText(braille);
                }
                try
                {
                    BufferedWriter out = new BufferedWriter(
                            new FileWriter(outfile, true));
                    out.write(text+"\n");
                    out.close();
                }
                catch (IOException except)
                {
                    except.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found at the specified location error>"+e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to write the file error>"+e.getMessage());
        }


    }


    /**
     * Class representing a node in a BrailleTree. Nodes without
     * associated text (e.g. inner nodes) should store the empty string
     * @author Catie Baker
     */
    private class BrailleNode {
        private String text;
        private BrailleNode left;
        private BrailleNode right;


        /**
         * Creates a BrailleNode with the provided text value
         * @param data the text to store in the node
         */
        public BrailleNode(String data) {
            this.text = data;
            this.right = null;
            this.left = null;
        }

        /**
         * Gets the text stored in the node
         * @return the text stored in the node
         */
        public String getText() {
            return text;
        }

        /**
         * Sets the text stored in the node
         * @param data the text to store in the node
         */
        public void setText(String data) {
            this.text = data;
        }

        /**
         * Gets the left child of the node
         * @return the left child of the node
         */
        public BrailleNode getLeft() {
            return left;
        }

        /**
         * Sets the left child of the node
         */
        public void setLeft(BrailleNode left) {
            this.left = left;
        }

        /**
         * Gets the right child of the node
         * @return the right child of the node
         */
        public BrailleNode getRight() {
            return right;
        }

        /**
         * Sets the right child of the node
         */
        public void setRight(BrailleNode right) {
            this.right = right;
        }


    }
}
