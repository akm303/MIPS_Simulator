package group1.mips_simulator.components.dataParts.instructionParts;


import group1.mips_simulator.Utility;

/**
 * A field is a value associated with an instruction operation.
 * Each field is (effectively) a binary number of a certain size.
 */
public class Field {

    public Field(short value_, short size_) {
        this.value = value_;
        this.size = size_;
    }

    public Field(int value_, short size_) {
        this((short) value_, size_);
    }

    public Field(short value_, int size_) {
        this(value_, (short) size_);
    }

    public Field(int value_, int size_) {
        this((short) value_, (short) size_);
    }

    /**
     * Construct a Field with a decimal string. NOT BINARY STRING
     */
    public Field(String value_, int size_) {
        if (!Utility.isNumeric(value_)) {
            throw new IllegalArgumentException("Can not create field from value string (decimal): " + value);
        }
        this.value = (short) Integer.parseInt(value_);
        this.size = (short) size_;
    }

    public short value; // The value of this field
    public short size; // The binary length of this field

    /**
     * Convert this Field into a binary string of the appropriate length
     *
     * @return A string representation of this binary number
     */
    public String toBinString() {
        StringBuilder result = new StringBuilder(this.size);

        // Initialize the result with the minimal binary representation
        result.append(Integer.toBinaryString(this.value));

        // Add 0s to the front of the result to reach the expected size
        while (result.length() < this.size) {
            result.insert(0, '0');
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return "Field{" +
                "value=" + value +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Field other = (Field) obj;
        return this.value == other.value &&
                this.size == other.size;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.value;
        hash = 53 * hash + this.size;
        return hash;
    }
}
