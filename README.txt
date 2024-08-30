The src/main/resources/TransactionsSample.json is sample data to test the code.

Project Prerequisites Setup
This directory contains scripts to automate the installation and configuration of prerequisites required to run the project.
By using these scripts, you can quickly set up your environment without manual installations.

Files Included:
prereqs.bat: Batch file for Windows to automate the setup process.
prereqs.sh: Shell script for Linux and macOS to automate the setup process.
setup/postgres.py: Python script responsible for installing and configuring PostgreSQL.

Prerequisites to be Installed:

Python: A programming language used for various purposes, including the setup script.
Download Python: https://www.python.org/
PostgreSQL: A powerful, open-source object-relational database system.
Download PostgreSQL: https://www.postgresql.org/download/
psycopg2 (for Python): A PostgreSQL database adapter for Python, required by the setup script.
If you're installing Python manually, you'll also need to install this library using pip install psycopg2-binary.

Windows Only: the Choclatey package manager will be installed if not found. This allows for the postgresql installation later.

Recommended Usage:
For a quick and hassle-free setup, it's highly recommended to run the appropriate script for your operating system:


Windows:

Open a Command Prompt or PowerShell window as an administrator.
Navigate to the project's root directory.
Run prereqs.bat.


Linux/macOS:

Open a terminal window.
Navigate to the project's root directory.
Make the script executable: chmod +x prereqs.sh
Run the script with sudo: sudo ./prereqs.sh
These scripts will handle the installation and configuration of Python (if not already present) and PostgreSQL,
ensuring you have the necessary prerequisites to run the project successfully.

Manual Installation:
If you prefer to install the prerequisites manually or encounter any issues with the scripts,
you can follow the download links provided above and install them according to the official instructions for your operating system.

Note: If you choose manual installation, make sure to set up the necessary environment variables and database configurations as required by the project.