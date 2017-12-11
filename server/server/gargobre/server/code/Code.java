package gargobre.server.code;

import java.io.IOException;
import java.util.Arrays;

public abstract class Code {

  /** The buffer where data is stored. */
  protected byte buf[];

  /** The number of valid bytes in the buffer. */
  protected int count;

  /**
   * 
   */
  public Code() {
    this(32);
  }

  /**
   * @param size
   */
  public Code(int size) {
    if (size < 0) {
      throw new IllegalArgumentException("Negative initial size: " + size);
    }
    buf = new byte[size];
  }

  public Code write(StringUTF string) {
    write(string.bytes());
    return this;
  }

  /**
   * Writes the specified byte to this byte array output stream.
   *
   * @param b the byte to be written.
   */
  public void write(int b) {
    ensureCapacity(count + 1);
    buf[count] = (byte) b;
    count += 1;
  }

  /**
   * Writes <code>b.length</code> bytes from the specified byte array to this
   * output stream. The general contract for <code>write(b)</code> is that it
   * should have exactly the same effect as the call
   * <code>write(b, 0, b.length)</code>.
   *
   * @param b the data.
   * @exception IOException if an I/O error occurs.
   * @see java.io.OutputStream#write(byte[], int, int)
   */
  public void write(byte b[]) {
    write(b, 0, b.length);
  }

  /**
   * Writes <code>len</code> bytes from the specified byte array starting at
   * offset <code>off</code> to this byte array output stream.
   *
   * @param b the data.
   * @param off the start offset in the data.
   * @param len the number of bytes to write.
   */
  public void write(byte b[], int off, int len) {
    if ((off < 0) || (off > b.length) || (len < 0) || ((off + len)
      - b.length > 0)) {
      throw new IndexOutOfBoundsException();
    }
    ensureCapacity(count + len);
    System.arraycopy(b, off, buf, count, len);
    count += len;
  }

  /**
   * Returns the current size of the buffer.
   *
   * @return the value of the <code>count</code> field, which is the number of
   *         valid bytes in this output stream.
   * @see java.io.ByteArrayOutputStream#count
   */
  public int size() {
    return count;
  }

  /**
   * Creates a newly allocated byte array. Its size is the current size of this
   * output stream and the valid contents of the buffer have been copied into
   * it.
   *
   * @return the current contents of this output stream, as a byte array.
   * @see java.io.ByteArrayOutputStream#size()
   */
  public byte[] bytes() {
    return Arrays.copyOf(buf, count);
  }

  /**
   * Increases the capacity if necessary to ensure that it can hold at least the
   * number of elements specified by the minimum capacity argument.
   *
   * @param minCapacity the desired minimum capacity
   * @throws OutOfMemoryError if {@code minCapacity < 0}. This is interpreted as
   *         a request for the unsatisfiably large capacity
   *         {@code (long) Integer.MAX_VALUE + (minCapacity - Integer.MAX_VALUE)}.
   */
  private void ensureCapacity(int minCapacity) {
    if (minCapacity - buf.length > 0) {
      int oldCapacity = buf.length;
      int newCapacity = oldCapacity << 1;
      if (newCapacity - minCapacity < 0) {
        newCapacity = minCapacity;
      }
      buf = Arrays.copyOf(buf, newCapacity);
    }
  }

}
