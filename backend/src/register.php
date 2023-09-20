<?php
include_once 'config/db_connection.php';
include_once 'config/cors.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $data = json_decode(file_get_contents("php://input"));

    $firstname = $data->firstname;
    $lastname = $data->lastname;
    $email = $data->email;
    $password = $data->password;

    $sql = $conn->query("SELECT id FROM Users WHERE email = '$email'");
    if ($sql->num_rows > 0) {
        http_response_code(200);
        echo json_encode(array('message' => 'Usuario ya está registrado', 'success' => false));
    } else {
        $password_hashed = password_hash($password, PASSWORD_DEFAULT);

        $sql = $conn->query("INSERT INTO Users (firstname, lastname, email, password) VALUES ('$firstname', '$lastname', '$email', '$password_hashed')");
        
        if ($sql) {
            http_response_code(200);
            echo json_encode(array('message' => 'Usuario registrado', 'success' => true));
        } else {
            http_response_code(400);
            echo json_encode(array('message' => 'Error, intenta otra vez', 'success' => false));
        }
    }
}
?>