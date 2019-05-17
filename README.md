# Unitymedia-Horizon-Connected-Devices-Web-App
This App is a small handy tool to quickly see connected devices to the Unitymedia Horizon and save them in a database. It is meant to be run continously on a dedicated machine so the logging is complete.

# Usage
This programm is a spring application, docker support is not yet implemented so gradle is mandatory. 
Download the zip an unpack it. Run the application with ./gradlew bootRun

# Setup correct login-credentials and ip
Before running the program you might want to setup your login-credentials and the ip. The default creds are 
user: admin, pw: admin, ip: 192.168.192.1
You must change the credentials in the HorizonConnectController.java and HttpConnectionClient.java (change the ip in this file)

# Connect to the wep app
The App runs on port 8080 on localhost: localhost:8080/
