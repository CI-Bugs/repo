import os
import sys
import subprocess
from subprocess import Popen
import datetime
import git
import shutil  
import csv

rootDir = r'/home/vinay/workspace/benchmark/autoExec'

astorRepo = 'https://github.com/SpoonLabs/astor.git'
CiBugsRepo = 'https://github.com/CI-Bugs/repo.git'

fCommands = open('commands.csv', 'r') 
bugCommands = fCommands.readlines()   

f = open(r'execLogs.txt', 'w+')

cmd = 'mkdir logs '        
subprocess.run(cmd, shell=True, cwd = rootDir)   

#Download Astor Repository
cmd = 'git clone ' + astorRepo        
subprocess.run(cmd, shell=True, cwd = rootDir)   

#root dir is astor now
rootDir = rootDir+'/astor'

#Astor Compile
cmd = 'mvn install -DskipTests=true'        
subprocess.run(cmd, shell=True, cwd = rootDir)

#Astor package
cmd = 'mvn package -DskipTests=true'
subprocess.run(cmd, shell=True, cwd = rootDir)

#Move and Rename the package to astor.jar
srcFolder = rootDir + "/target/astor-1.0.0-SNAPSHOT-jar-with-dependencies.jar"
cmd = 'mv ' + srcFolder + ' ' + rootDir
subprocess.run(cmd, shell=True, cwd = rootDir)

srcFolder = rootDir + "/astor-1.0.0-SNAPSHOT-jar-with-dependencies.jar"
cmd = 'mv ' + srcFolder + ' ' + rootDir + '/astor.jar'
subprocess.run(cmd, shell=True, cwd = rootDir)

#Download CI BUgs Repository
cmd = 'git clone ' + CiBugsRepo        
subprocess.run(cmd, shell=True, cwd = rootDir)   

#Create backup for CI Bugs Repository
cmd = 'mv repo repo_bkup'
subprocess.run(cmd, shell=True, cwd = rootDir)

repoDir = rootDir + "/repo"

f.write("repo : " + CiBugsRepo + "\n")
f.flush()
for bugCommand in bugCommands[1:]:

    bugCommand = bugCommand.split(",")  

    if(len(bugCommand) != 9):
        continue

    bugId = bugCommand[0]
    buggyCommit = bugCommand[1]
    failingCases = bugCommand[3]
    javaCompliance = bugCommand[4]
    srcjavafolder = bugCommand[5]
    srctestfolder = bugCommand[6]
    binjavafolder = bugCommand[7]
    bintestfolder = bugCommand[8]    

    cmd = 'cp -r repo_bkup repo'
    subprocess.run(cmd, shell=True, cwd = rootDir)          

    cmd = 'git checkout ' + bugId     
    f.write("repo : " + cmd + "\n")      
    f.flush()     
    subprocess.run(cmd, shell=True, cwd = repoDir)     

    cmd = 'git fetch origin ' + buggyCommit
    f.write("repo : " + cmd + "\n")      
    f.flush()         
    subprocess.run(cmd, shell=True, cwd = repoDir)   

    cmd = 'git reset --hard ' + buggyCommit
    f.write("repo : " + cmd + "\n")    
    f.flush()           
    subprocess.run(cmd, shell=True, cwd = repoDir)          

    cmd = 'git rev-parse --verify HEAD'
    f.write("repo : " + cmd + "\n")    
    f.flush()           
    subprocess.run(cmd, shell=True, cwd = repoDir)         

    cmd = 'mvn compile > ../../logs/' + bugId + "_compile.txt"
    f.write("repo : " + cmd + "\n")           
    subprocess.run(cmd, shell=True, cwd = repoDir)    

    cmd = 'mvn test > ../../logs/' + bugId + "_test.txt"
    f.write("repo : " + cmd + "\n")  
    f.flush()             
    subprocess.run(cmd, shell=True, cwd = repoDir)        

    cmd  = 'mvn dependency:build-classpath -B | egrep -v "(^\[INFO\]|^\[WARNING\])"'
    dependencies = str(subprocess.Popen(cmd, shell=True, cwd = repoDir, stdout=subprocess.PIPE ).communicate()[0], 'utf-8')  
    dependencies = dependencies.strip("\n")
    cmd = 'java -cp astor.jar fr.inria.main.evolution.AstorMain -flthreshold 0.5 -maxtime 5 -stopfirst false -scope package ' 

    cmd = cmd + " -location " + rootDir + "/repo " 
    cmd = cmd + " -failing " + failingCases.strip("\n")
    cmd = cmd + " -dependencies " +  dependencies.strip("\n")
    cmd = cmd + " -srcjavafolder " + srcjavafolder.strip("\n")
    cmd = cmd + " -srctestfolder " + srctestfolder.strip("\n")    
    cmd = cmd + " -binjavafolder " + binjavafolder.strip("\n")       
    cmd = cmd + " -bintestfolder " + bintestfolder.strip("\n")

    tools = ['jkali', 'jgenprog', 'cardumen']
    for tool in tools :   
        execCmd = cmd + " -mode "  + tool + ' > ../logs/' + bugId + "_" + tool+'.txt'
        f.write("repo : " + execCmd + "\n")           
        f.flush()        
        subprocess.run(execCmd, shell=True, cwd = rootDir)   

    cmd = 'rm -rf repo'
    subprocess.run(cmd, shell=True, cwd = rootDir)                       
    f.write("repo : " + execCmd + "\n")      
    f.flush()                       
f.close()

#Create backup for CI Bugs Repository
cmd = 'mv repo_bkup repo'
subprocess.run(cmd, shell=True, cwd = rootDir)
  




