package co.com.coomeva.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import co.com.coomeva.util.acceso.UtilAcceso;

/**
 * Centraliza utilidades para el manejo de archivos
 * 
 * @author E. Alexander Ospina Castiblanco
 */
public class ManejoArchivos {

	public static final int EXITO = -1;
	public static final int ERROR = 1;

	/**
	 * Almacena los bytes en el archivo enviado como parametro
	 * 
	 * @param archivo
	 *            arhivo a guardar los bytes
	 * @param fileByte
	 *            arreglo de bytes a ser guardados
	 * @return return ManejoArchivos.EXITO o ERROR
	 */
	public static int guardar(File archivo, byte[] fileByte) {
		int estado = -1;
		FileOutputStream fileOut;
		if (archivo != null) {
			try {
				// si existe el archivo lo elimina
				archivo.delete();
				// crear nuevamente el archivo
				fileOut = new FileOutputStream(archivo);
				fileOut.write(fileByte);
				fileOut.flush();
				fileOut.close();
				estado = EXITO;
			} catch (IOException e) {
				e.printStackTrace();
				estado = ERROR;
			}
		}
		return estado;
	}

	/**
	 * Extracto de la página: http://www.rgagnon.com/javadetails/java-0064.html
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void copyFile(File in, File out) throws IOException {
		FileChannel inChannel = new FileInputStream(in).getChannel();
		FileChannel outChannel = new FileOutputStream(out).getChannel();
		try {
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} catch (IOException e) {
			throw e;
		} finally {
			if (inChannel != null)
				inChannel.close();
			if (outChannel != null)
				outChannel.close();
		}
	}

	/**
	 * Extracto de la pagina:
	 * http://www.java2s.com/Code/Java/File-Input-Output/CopyfilesusingJavaIOAPI.htm
	 * 
	 * @param fromFileName
	 * @param toFileName
	 * @throws IOException
	 */
	public static void copy(String fromFileName, String toFileName)
			throws IOException {
		File fromFile = new File(fromFileName);
		File toFile = new File(toFileName);

		if (!fromFile.exists())
			throw new IOException("FileCopy: " + "no such source file: "
					+ fromFileName);
		if (!fromFile.isFile())
			throw new IOException("FileCopy: " + "can't copy directory: "
					+ fromFileName);
		if (!fromFile.canRead())
			throw new IOException("FileCopy: " + "source file is unreadable: "
					+ fromFileName);

		if (toFile.isDirectory())
			toFile = new File(toFile, fromFile.getName());

		if (toFile.exists()) {
			if (!toFile.canWrite())
				throw new IOException("FileCopy: "
						+ "destination file is unwriteable: " + toFileName);
			System.out.print("Overwrite existing file " + toFile.getName()
					+ "? (Y/N): ");
			System.out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			String response = in.readLine();
			if (!response.equals("Y") && !response.equals("y"))
				throw new IOException("FileCopy: "
						+ "existing file was not overwritten.");
		} else {
			String parent = toFile.getParent();
			if (parent == null)
				parent = System.getProperty("user.dir");
			File dir = new File(parent);
			if (!dir.exists())
				throw new IOException("FileCopy: "
						+ "destination directory doesn't exist: " + parent);
			if (dir.isFile())
				throw new IOException("FileCopy: "
						+ "destination is not a directory: " + parent);
			if (!dir.canWrite())
				throw new IOException("FileCopy: "
						+ "destination directory is unwriteable: " + parent);
		}

		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(fromFile);
			to = new FileOutputStream(toFile);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) != -1)
				to.write(buffer, 0, bytesRead); // write
		} finally {
			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
					;
				}
			if (to != null)
				try {
					to.close();
				} catch (IOException e) {
					;
				}
		}
	}

	/**
	 * Copiar directorio local a otro local
	 * 
	 * @param sourceDir
	 * @param destDir
	 * @throws IOException
	 */
	public static void copyDirectory(File sourceDir, File destDir)
			throws IOException {

		if (!destDir.exists()) {

			destDir.mkdir();

		}

		File[] children = sourceDir.listFiles();

		for (File sourceChild : children) {

			String name = sourceChild.getName();

			File destChild = new File(destDir, name);

			if (sourceChild.isDirectory()) {

				copyDirectory(sourceChild, destChild);

			}

			else {

				copyFile(sourceChild, destChild);

			}

		}

	}

	/**
	 * Copiar recurso local a otro local.
	 * 
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFile2(File source, File dest) throws IOException {

		if (!dest.exists()) {

			dest.createNewFile();

		}

		InputStream in = null;

		OutputStream out = null;

		try {

			in = new FileInputStream(source);

			out = new FileOutputStream(dest);

			byte[] buf = new byte[1024];

			int len;

			while ((len = in.read(buf)) > 0) {

				out.write(buf, 0, len);

			}

		}

		finally {

			in.close();

			out.close();

		}

	}

	/**
	 * Borrado de recurso local
	 * 
	 * @param resource
	 * @param withRoot
	 * @return
	 * @throws IOException
	 */
	public static boolean delete(File resource, boolean withRoot)
			throws IOException {

		if (resource.isDirectory()) {

			File[] childFiles = resource.listFiles();

			for (File child : childFiles) {

				delete(child);

			}

		}
		if (withRoot)
			return resource.delete();
		else
			return true;

	}

	public static boolean delete(File resource) throws IOException {

		return delete(resource, true);

	}

	/**
	 * Copia un directorio remoto a otro remoto
	 * 
	 * @param sourceDir
	 * @param destDir
	 * @throws IOException
	 */
	public static void copyDirectory(SmbFile sourceDir, SmbFile destDir)
			throws IOException {

		if (!destDir.exists()) {

			destDir.mkdir();

		}

		SmbFile[] children = sourceDir.listFiles();

		for (SmbFile sourceChild : children) {

			String name = sourceChild.getName();

			SmbFile destChild = new SmbFile(destDir, name);

			if (sourceChild.isDirectory()) {

				copyDirectory(sourceChild, destChild);

			}

			else {

				// copyFile(sourceChild, destChild);
				copyFile(sourceChild, destChild);
			}

		}

	}

	/**
	 * Copia un recurso remoto a otro remoto
	 * 
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFile(SmbFile source, SmbFile dest)
			throws IOException {

		if (!dest.exists()) {

			dest.createNewFile();

		}

		InputStream in = null;

		OutputStream out = null;

		try {

			in = source.getInputStream();

			out = dest.getOutputStream();

			byte[] buf = new byte[1024];

			int len;

			while ((len = in.read(buf)) > 0) {

				out.write(buf, 0, len);

			}

		}

		finally {

			in.close();

			out.close();

		}

	}

	/**
	 * Borrado de recurso remoto, si es un directorio y withRoot es verdadero,
	 * borra todo hasta la cabeza del directorio.
	 * 
	 * @param resource
	 * @param withRoot
	 * @return
	 * @throws Exception
	 */
	public static boolean delete(SmbFile resource, boolean withRoot)
			throws Exception {

		if (resource.isDirectory()) {

			SmbFile[] childFiles = resource.listFiles();

			for (SmbFile child : childFiles) {

				delete(child);

			}

		}

		if (withRoot) {
			try {
				resource.delete();
				return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				UtilAcceso.printStackTrace(e, true);
				return false;
			}
		} else
			return true;

	}

	/**
	 * Borrado de un recurso remoto desde sus hijos hasta la cabeza del
	 * directorio.
	 * 
	 * @param resource
	 * @return
	 * @throws Exception
	 */
	public static boolean delete(SmbFile resource) throws Exception {

		return delete(resource, true);

	}

	/**
	 * Copia de un directorio remoto a un directorio local
	 * 
	 * @param sourceDir
	 * @param destDir
	 * @throws IOException
	 */
	public static void copyDirectory(SmbFile sourceDir, File destDir)
			throws IOException {

		if (!destDir.exists()) {

			destDir.mkdir();

		}

		SmbFile[] children = sourceDir.listFiles();

		for (SmbFile sourceChild : children) {

			String name = sourceChild.getName();

			File destChild = new File(destDir, name);

			if (sourceChild.isDirectory()) {

				copyDirectory(sourceChild, destChild);

			}

			else {

				// copyFile(sourceChild, destChild);
				copyFile(sourceChild, destChild);
			}

		}

	}

	/**
	 * Copia un archivo remoto en un archivo local
	 * 
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFile(SmbFile source, File dest)
			throws IOException {

		if (!dest.exists()) {

			dest.createNewFile();

		}

		SmbFileInputStream in = null;

		FileOutputStream out = null;

		try {

			in = new SmbFileInputStream(source);

			out = new FileOutputStream(dest);

			byte[] buf = new byte[1024];

			int len;

			while ((len = in.read(buf)) > 0) {

				out.write(buf, 0, len);

			}

		}

		finally {

			in.close();

			out.close();

		}

	}

	/**
	 * Copia un directorio desde una ruta local a una remota
	 * 
	 * @param sourceDir
	 * @param destDir
	 * @throws IOException
	 */
	public static void copyDirectory(File sourceDir, SmbFile destDir)
			throws IOException {

		if (!destDir.exists()) {

			destDir.mkdir();

		}

		File[] children = sourceDir.listFiles();

		for (File sourceChild : children) {

			String name = sourceChild.getName();

			SmbFile destChild = new SmbFile(destDir, name);

			if (sourceChild.isDirectory()) {

				copyDirectory(sourceChild, destChild);

			}

			else {

				// copyFile(sourceChild, destChild);
				copyFile(sourceChild, destChild);
			}

		}

	}

	/**
	 * Copia un archivo local a un archivo remoto
	 * 
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFile(File source, SmbFile dest)
			throws IOException {

		if (!dest.exists()) {

			dest.createNewFile();

		}

		InputStream in = null;

		OutputStream out = null;

		try {

			in = new FileInputStream(source);

			out = dest.getOutputStream();

			byte[] buf = new byte[1024];
			
			int len;

			while ((len = in.read(buf)) > 0) {

				out.write(buf, 0, len);

			}

		}

		finally {

			in.close();

			out.close();

		}

	}

	public static boolean moveDirectoryWithRoot(File source, File dest) {
		return moveDirectory(source, dest, true);
	}

	public static boolean moveDirectory(File source, File dest, boolean withRoot) {

		try {

			// ManejoArchivos.copyFile(origen, destino);
			copyDirectory(source, dest);
			delete(source, withRoot);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			UtilAcceso.printStackTrace(e, true);
		}
		return false;

	}

}
