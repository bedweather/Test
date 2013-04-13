package test.jcifs;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;



public class JcifsTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	jcifs.Config.setProperty("jcifs.netbios.wins", "10.0.0.7");
	NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", "zq", "smb850912");
//	System.out.println(auth.toString()+"");
	try {
	    SmbFile smbfile = new SmbFile("smb://SERVER-ZQ/share/a.txt",auth);
//	    SmbFile smbfile = new SmbFile("smb://SERVER-ZQ/share/new file");
	    System.out.println(smbfile.getName());
//	    InputStream smbIS = smbfile.getInputStream();
	    SmbFileInputStream sfis = new SmbFileInputStream(smbfile);
	    BufferedInputStream bs = new BufferedInputStream(sfis);
	    FileOutputStream fs = new FileOutputStream("a.txt");
//	    BufferedInputStream bs = BufferedInputStream(fs);
	    FileChannel  out = fs.getChannel();
	    byte[] buf = new byte[8192];
	    int  n;
	    ByteBuffer bb;
	    while((n = bs.read(buf)) > 0 ) {
		    bb = ByteBuffer.wrap(buf);
		    System.out.write( buf, 0, n );
		    while(bb.hasRemaining()) {
			out.write(bb);
		    }
		    bb.clear();
	    }
	    
	} catch (MalformedURLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SmbException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {

    }


    }
    
    

}
