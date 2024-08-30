#!/bin/bash

# Check if running as root
if [[ $EUID -ne 0 ]];
then
    echo "This script requires root privileges. Please run it with sudo."
    read -p "Press Enter to exit..."
    exit 1
fi

# Check if Python is installed
if ! command -v python3 &> /dev/null
then
    echo "Python is not installed. Installing the latest version..."
    if command -v apt-get &> /dev/null; then  # Debian/Ubuntu
        sudo apt-get update
        sudo apt-get install -y python3 python3-pip
    elif command -v brew &> /dev/null; then  # macOS
        brew install python3
    else
        echo "Unsupported package manager. Please install Python manually."
        exit 1
    fi
fi


# Prompt for PostgreSQL username and password
read -p "Enter PostgreSQL username (if already installed, use your existing username): " POSTGRES_USER
read -s -p "Enter PostgreSQL password (if already installed, use your existing password): " POSTGRES_PASSWORD
echo ""  # Add a newline after the password input

# Run the Python script, passing the username and password as arguments
python3 setup/postgres.py "$POSTGRES_USER" "$POSTGRES_PASSWORD"

# Pause to allow the user to see the output
read -p "Press Enter to continue..."