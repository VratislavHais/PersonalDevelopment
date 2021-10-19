provider "aws" {
    access_key = ""
    secret_key = ""
}

resource "aws_instance" "some-name" {
    ami = "" # napr rhel ami -> ziskame z aws
    instance_type = "t2.micro"
    tags = {
        Name = "rhel"
    }
}