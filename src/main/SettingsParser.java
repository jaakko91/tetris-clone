package main;

import java.io.*;

//This class reads and writes setting to a file.
//Currently there aren't any real "settings" but only the top score is saved to a file so it is stored between game sessions.
//This can be expanded in the future to add more settings/parameters.
public class SettingsParser {
	private String filename = "top.bin";
	
	public SettingsParser(){
	}
	
	//Read a parameter/setting from a file.
	//Returns null if the settings file was not found or if the parameter was not found.
	//If the game will be expanded the have more options, this should be updated to read all options at once, otherwise it is slow.
	public String load(String name){
		String result=null;
		boolean found=false;
		try{
			FileInputStream fstream=null;
			try{
				fstream = new FileInputStream(filename);
			}
			catch (FileNotFoundException e){return null;}
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line=null;
			//Go through the file line by line.
			while ((line = br.readLine()) != null)
				//If line is not a comment and contains the searched parameter, parse it to the result.
				if(!line.startsWith("#") && line.trim().startsWith(name)){
					result=line.split("=")[1].trim();
					found=true;
				}
			in.close();
		}catch (Exception e){e.printStackTrace();}
		if(found)  
			return result;
		else 
			return null;
	}
	
	//Write settings to a file.
	//Receives a two-dimensional array as a parameter.
	//First field is the name of the setting to be written and second is the value.
	public void write(String [][] writethis){		
		boolean exists = false;
		//Check is the file already exists.
		if(new File(filename).exists())
			exists = true;
		try{
			File settings = new File(filename);
			File temp = new File("settings.temp");
			BufferedWriter writer = new BufferedWriter( new FileWriter(settings,true) );
			//If file already exists, write file again but leave out parameters which are to be written.
			//Then append the updated values of the settings to be written to the end of the file. 
			if(exists){
				DataInputStream in = new DataInputStream(new FileInputStream(filename));
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				BufferedWriter tempwriter = new BufferedWriter( new FileWriter(temp,true) );
				String line = null;	
				//Go through the current settings file line by line.
				while ((line = reader.readLine()) != null){
					boolean write=true;
					for(int i=0;i<writethis.length;i++){
						//If read line contains any of the parameters to be written, don't write it, but only append the updated value to the end. 
						if(line.trim().startsWith(writethis[i][0])){
							write=false;
							i=writethis.length;
						}	
					}
					//If it is not going to be updated, just write it to the new file.
					if (write){
						tempwriter.write(line);tempwriter.newLine();
					}
				}
				//Append the new or updated settings to the file.
				for(int i=0;i<writethis.length;i++){
					tempwriter.write(writethis[i][0]+"="+writethis[i][1]);
					tempwriter.newLine();
				}
				tempwriter.close();
				writer.close();
				in.close();
				settings.delete();
				temp.renameTo(settings);
			}
			//File does not exist, write all parameters to a new file.
			else{
				for(int i=0;i<writethis.length;i++){
					writer.write(writethis[i][0]+"="+writethis[i][1]);
					writer.newLine();
				}
				writer.close();
			}
		}catch(Exception e){e.printStackTrace();}
	}

}
