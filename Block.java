/**
 * Represents a single block in a C program with variables.
 */
public class Block {
    private Variable[] block = new Variable[10];
    private int curPos = 0;

    // Getters and Setters
    public Variable[] getBlock() {
        return block;
    }
    public void setBlock(Variable[] block) {
        this.block = block;
    }
    public int getCurPos() {
        return curPos;
    }
    public void setCurPos(int curPos) {
        this.curPos = curPos;
    }

    /**
     * Constructs an empty Block object.
     * 
     * @custom.postcondition The block is initialized with a capacity for 10 variables and curPos is set to 0 by default.
     */
    public Block(){}

    /**
     * Constructs a Block object with the given array of variables.
     * 
     * @param var The array of variables to initialize the block with.
     * @custom.precondition The 'var' array should not be null.
     */
    public Block(Variable[] var){
        block = var;
    }

    /**
     * Adds a variable to the block.
     * 
     * @param var The variable to be added to the block.
     * @custom.precondition The 'var' object should not be null and the block should not be full.
     * @custom.postcondition The provided variable is added to the block and curPos is incremented.
     */
    public void addVar(Variable var){
        block[curPos] = var; 
        curPos++;
    }

    /**
     * Finds a variable in the block by its name.
     * 
     * @param name The name of the variable to search for.
     * @return The variable with the matching name, or null if not found.
     * @custom.precondition The 'name' string should not be null or empty.
     * @custom.postcondition If a variable with the given name is found, it is returned, otherwise null is returned.
     */
    public Variable findVal(String name){
        if (name == null) return null;

        for (Variable var: block){
            if (var != null){
                if (name.equals(var.getName())) return var;
            } else {break;}
        }

        return null;
    }

    /**
     * Returns a string representation of the block, listing all variables.
     * 
     * @return A string representation of the block.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Variable a : block) {
            if (a != null) {
                sb.append(a.toString()).append("\n"); 
            }
        }
        return sb.toString();
    }
}
