# comandos quando criar o ec2 no aws 

sudo apt install update -y && sudo apt install upgrade -y 

sudo apt install opensdk-21 -y 

sudo apt install maven 


# Clonando projeto para o ec2 

somente dar o clone 

# instalando docker no servidor

sudo apt update
sudo apt install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt update
sudo apt install docker-ce
sudo systemctl status docker
sudo usermod -aG docker ${USER}
sudo usermod -aG docker username 
REINICIAR A MÁQUINA...
