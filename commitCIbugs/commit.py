import os
import sys
import subprocess
from subprocess import Popen
import datetime
import git
import shutil  
import csv

repoDir = r'/home/vinay/workspace/benchmark/cibugs'

#reportFile = open(r'C:\PHD\CI_Bugs\pushCommitreport.txt', 'a+')

hardCheckout = True
srcFolder = ''
destFolder = 'repo'

repoName = 'git clone https://github.com/CI-Bugs/repo.git/'

subprocess.call(repoName, shell=True, cwd = repoDir)  

with open(r'/home/vinay/workspace/benchmark/cibugs/FailingPassing.csv') as csv_file:

    csv_reader = csv.reader(csv_file, delimiter=',')
    rows = list(csv_reader)

    for row in rows[1:]:

        branchName  = row[0]

        #Create a new branch in dest repo to checkin the commit
        #cmd = 'git checkout -b ' + branchName
        cmd = 'git checkout --orphan ' + branchName        
        subprocess.call(cmd, shell=True, cwd = repoDir+"/"+destFolder)           

        print(row)

        line = row[1].strip()

        print(line)

        url_repo = line.split("commit")[0]
        srcFolder = url_repo.split("/")[-2]

        print("Project Name : ", srcFolder)

        url_repo = url_repo[0:-1] + str(".git")

        print ("Repo URL : ", url_repo)   

        cmd = 'git clone ' + url_repo
        subprocess.call(cmd, shell=True, cwd = repoDir)      

        msgCommit  = 'Buggy Commit'

        for line in row[1:]:
            commit = line.split("commit")[1][1:]

            print ("Commit : ", commit)

            if(hardCheckout == False):
                cmd = 'git checkout ' + commit
                subprocess.call(cmd, shell=True, cwd = repoDir+"/"+srcFolder)     
            else:
                cmd = 'git fetch origin ' + commit            
                subprocess.call(cmd, shell=True, cwd = repoDir+"/"+srcFolder)  

                cmd = 'git reset --hard ' + commit            
                subprocess.call(cmd, shell=True, cwd = repoDir+"/"+srcFolder)  

                cmd = 'git rev-parse --verify HEAD '         
                subprocess.call(cmd, shell=True, cwd = repoDir+"/"+srcFolder)                                  

            cmd = "cp -r * ../"+destFolder
            subprocess.call(cmd, shell=True, cwd = repoDir+"/"+srcFolder)  
            cmd = "git add ."

            subprocess.call(cmd, shell=True, cwd = repoDir+"/"+destFolder)        

            cmd = "git commit --allow-empty -m '" + msgCommit + " : " + line + "'"
            #cmd = "git commit -m '" + msgCommit + " : " + line + "'"
            #cmd = "git commit -m 'comiit'"
            subprocess.call(cmd, shell=True, cwd = repoDir+"/"+destFolder)  

            cmd = "git push origin " + branchName
            subprocess.call(cmd, shell=True, cwd = repoDir+"/"+destFolder)  

            msgCommit  = 'Fixed Commit'     

        cmd = "rm -rf " + srcFolder
        subprocess.call(cmd, shell=True, cwd = repoDir)  

        cmd = "rm -rf " + destFolder + "/*"
        subprocess.call(cmd, shell=True, cwd = repoDir)  




