ALTER TABLE transactions
ADD COLUMN destination_account_id INT,
ADD CONSTRAINT fk_destination_account
FOREIGN KEY (destination_account_id) REFERENCES accounts(id);
