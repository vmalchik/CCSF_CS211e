/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Quiz: 1
 * File: FileLoaderDemo.java
 */

package edu.ccsf.quiz;

import java.io.IOException;

public class FileLoaderDemo
{
	public static void main(String[] args) throws IOException
	{
		//obtain file contents from a local system 
		String[] a = FileLoader.loadfile("readme.txt");
		System.out.println("Here are the contents of readme.txt:");
		for(int i = 0; i < a.length; i++)
			System.out.println(a[i]);
		
		System.out.println();
		
		//obtain contents from a remote file using a URL 
		a = FileLoader.loadfile("http://hills.ccsf.edu/~vmalchik/text");
		System.out.println("Here are the contents of text file located on hills:");
		for(int i = 0; i < a.length; i++)
			System.out.println(a[i]);

	}
}
