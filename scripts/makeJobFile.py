with open('job.txt', 'w') as f:
    f.write("jar_files        = Rotoscoper.jar" +'\n' 
    +"executable 			  = C:\Program Files\AdoptOpenJDK\jdk-11.0.10.9-hotspot\bin\java.exe" +'\n' 
    +"log                     = Counter.log" +'\n' 
    +"error                   = errors.txt" +'\n' 
    +"output 				  = output.txt" +'\n' 
    +"transfer_input_files    = Rotoscoper.jar, videos" +'\n' 
    +"should_transfer_files   = YES" +'\n'
    +"transfer_executable     = false" +'\n'
    +"Requirements   		  = OpSys == ""WINDOWS"" && Arch ==""X86_64"""+ '\n')
    n = 42
    for x in range(n):
        f.write("arguments    = -jar -Xss1048576 Rotoscoper.jar videos/"+str(x+1) +".mp4 output" +str(x+1) +'\n' 
        +"queue"+'\n')
