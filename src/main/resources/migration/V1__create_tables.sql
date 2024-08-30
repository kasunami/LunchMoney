-- Create the account_users table
CREATE TABLE account_users (
                               id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                               account_id VARCHAR NOT NULL UNIQUE,
                               first_name VARCHAR NOT NULL,
                               last_name VARCHAR NOT NULL
);

-- Create the transactions table
CREATE TABLE transactions (
                              id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                              date DATE NOT NULL,
                              item VARCHAR NOT NULL,
                              amount DOUBLE PRECISION NOT NULL,
                              quantity INTEGER NOT NULL,
                              account_balance_id VARCHAR,
                              first_name VARCHAR NOT NULL,
                              FOREIGN KEY (account_balance_id) REFERENCES account_users(account_id)
);