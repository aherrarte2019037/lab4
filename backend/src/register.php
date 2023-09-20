<?php
include_once 'config/db_connection.php';
include_once 'config/cors.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $data = json_decode(file_get_contents("php://input"));

    $firstname = $data->firstname;
	$lastname = $data->lastname;
	$email = $data->email;
    $password = $data->password;

    $password_hashed = password_hash($password, PASSWORD_DEFAULT);

    $sql = $conn->query("INSERT INTO Users (firstname, lastname, email, password) VALUES ('$firstname', '$lastname', '$email', '$password_hashed')");
    
    if ($sql) {
        http_response_code(201);
        echo json_encode(array('message' => 'Usuario registrado', 'success' => true));
    } else {
        http_response_code(500);
        echo json_encode(array('message' => 'Error en el servidor interno', 'success' => false));
    }

} else {
    http_response_code(404);
}