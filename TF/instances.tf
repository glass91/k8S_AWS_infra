resource "aws_instance" "k8s_Instance" {
  count                  = 3
  ami                    = "ami-053b0d53c279acc90" # Ubuntu server 22.04
  instance_type          = "t2.micro" # Free TIER
  vpc_security_group_ids = [aws_security_group.web-sg.id]
  key_name               = "TestInstance2Last"
  tags = {
    Name = "k8s_Instance"
  }
}