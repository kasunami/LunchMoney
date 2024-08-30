import os
import platform
import subprocess
import sys

def check_and_install_packages():
    """Checks if required packages are installed and installs them if not."""
    try:
        import psycopg2
    except ImportError:
        print("psycopg2 not found. Installing...")
        subprocess.check_call([sys.executable, "-m", "pip", "install", "psycopg2-binary"])

def check_postgres_instance():
    """Checks if a PostgreSQL instance is running."""
    try:
        subprocess.check_output(["pg_isready"])
        return True
    except subprocess.CalledProcessError:
        return False

def install_postgres():
    """Installs PostgreSQL using Chocolatey on Windows, otherwise uses system package managers."""
    if platform.system() == "Linux":
        subprocess.run(["sudo", "apt-get", "update"])
        subprocess.run(["sudo", "apt-get", "install", "-y", "postgresql", "postgresql-contrib"])
    elif platform.system() == "Darwin":  # macOS
        subprocess.run(["brew", "install", "postgresql"])
    elif platform.system() == "Windows":
        # Check if Chocolatey is installed
        try:
            subprocess.check_output(["choco", "--version"])
        except subprocess.CalledProcessError:
            print("Chocolatey is not installed. Installing...")
            # Install Chocolatey (requires administrative privileges)
            subprocess.check_call(
                [
                    'powershell',
                    '-NoProfile',
                    '-ExecutionPolicy',
                    'Bypass',
                    '-Command',
                    "iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))"

                ]
            )
        # Install PostgreSQL using Chocolatey
        subprocess.check_call(["choco", "install", "postgresql", "-y"])
    else:
        print("Unsupported operating system. Please install PostgreSQL manually.")
        exit(1)


def create_database(conn):
    """Creates the 'lunch_money' database."""
    with conn.cursor() as cur:
        cur.execute("CREATE DATABASE lunch_money;")
    conn.commit()

if __name__ == "__main__":
    # Check and install required packages
    check_and_install_packages()

    # Get user/password input
    if len(sys.argv) == 3:
        username = sys.argv[1]
        password = sys.argv[2]
    else:
        username = input("Enter PostgreSQL username: ")
        password = input("Enter PostgreSQL password: ")

    # Check for PostgreSQL instance
    if not check_postgres_instance():
        print("PostgreSQL is not installed. Installing...")
        install_postgres()

    # Connect to PostgreSQL
    try:
        import psycopg2
        conn = psycopg2.connect(
            dbname="postgres",
            user=username,
            password=password,
            host="localhost"
        )
    except psycopg2.OperationalError as e:
        print(f"Error connecting to PostgreSQL: {e}")
        print("Invalid credentials for PostgreSQL instance. Please provide valid user/password.")
        exit(1)

    # Create the 'lunch_money' database
    create_database(conn)
    conn.close()

    print("PostgreSQL is installed and running with the new 'lunch_money' database created. Success!")