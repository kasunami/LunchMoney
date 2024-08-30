import { useState } from 'react';
import { Container, Row, Col, Button, Table, Modal } from 'react-bootstrap';
import axios from 'axios';

interface AccountUserDTO {
    id: string;
    firstName: string;
    lastName: string;
    accountBalanceId: string;
}

interface TransactionDTO {
    date: string;
    item: string;
    amount: number;
    quantity: number;
}

function App() {
    const [users, setUsers] = useState<AccountUserDTO[]>([]);
    const [transactions, setTransactions] = useState<TransactionDTO[]>([]);
    const [showTransactions, setShowTransactions] = useState(false);
    const [currentUser, setCurrentUser] = useState<string | null>(null);

    const fetchUsers = async () => {
        try {
            const response = await axios.get<AccountUserDTO[]>('/accountUsers/allUsers');
            setUsers(response.data);
        } catch (error) {
            console.error('Error fetching users:', error);
        }
    };

    const fetchTransactions = async (accountBalanceId: string) => {
        try {
            const response = await axios.get<TransactionDTO[]>(`/transactions/byAccount/${accountBalanceId}`);
            setTransactions(response.data);
            setCurrentUser(accountBalanceId);
            setShowTransactions(true);
        } catch (error) {
            console.error(`Error fetching transactions for accountBalanceId ${accountBalanceId}:`, error);
        }
    };

    return (
        <>
            <Container fluid>
                <Row className="min-vh-100">
                    <Col xs={2} className="bg-light d-flex flex-column align-items-start p-3">
                        <Button variant="primary" onClick={fetchUsers} className="mb-2">
                            Get All Users
                        </Button>
                    </Col>
                    <Col xs={10} className="d-flex justify-content-center align-items-center">
                        <Table striped bordered hover>
                            <thead>
                            <tr>
                                <th>Account Balance ID</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {users.map((user) => (
                                <tr key={user.id}>
                                    <td>{user.accountBalanceId}</td>
                                    <td>{user.firstName}</td>
                                    <td>{user.lastName}</td>
                                    <td>
                                        <Button
                                            variant="secondary"
                                            onClick={() => fetchTransactions(user.accountBalanceId)}
                                        >
                                            Get Transactions
                                        </Button>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </Table>
                    </Col>
                </Row>
            </Container>

            <Modal show={showTransactions} onHide={() => setShowTransactions(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Transactions for Account ID: {currentUser}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Table striped bordered hover>
                        <thead>
                        <tr>
                            <th>Date</th>
                            <th>Item</th>
                            <th>Amount</th>
                            <th>Quantity</th>
                        </tr>
                        </thead>
                        <tbody>
                        {transactions.map((transaction, index) => (
                            <tr key={index}>
                                <td>{transaction.date}</td>
                                <td>{transaction.item}</td>
                                <td>{transaction.amount}</td>
                                <td>{transaction.quantity}</td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => setShowTransactions(false)}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default App;