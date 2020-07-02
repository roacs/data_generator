package abstraction.immutable;

import java.nio.ByteBuffer;

public class Utility
{
    public static void printBuffer(ByteBuffer b)
    {
        // TODO get from real implementation
        StringBuilder builder = new StringBuilder();
        
        int remaining = b.remaining();
        for (int i = 0; i < remaining; i++)
        {
            builder.append(String.valueOf(b.get(i)));
        }
        
        System.out.println(builder.toString());
    }
}
