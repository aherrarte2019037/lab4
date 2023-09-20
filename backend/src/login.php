<?php
include_once 'config/db_connection.php';
include_once '../vendor/autoload.php';

use \Firebase\JWT\JWT;

include_once 'config/cors.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $data = json_decode(file_get_contents("php://input"));

    $umail = $data->email;
    $pass = $data->password;

    $sql = $conn->query("SELECT * FROM Users WHERE email = '$umail'"); 
    if ($sql->num_rows > 0) {
        $user = $sql->fetch_assoc();
        if (password_verify($pass, $user['password'])) {
            $key = "Lab-4-Secret-Key";
            $payload = array(
                'id' => $user['id'],
                'email' => $user['email'],
                'firstname' => $user['firstname'],
                'lastname' => $user['lastname']
            );

            $token = JWT::encode($payload, $key, 'HS256');
            http_response_code(200);
            echo json_encode(array('token' => $token, 'message' => 'Inicio de sesión exitoso', 'data' => $payload, 'success' => true));
        } else {
            http_response_code(200);
            echo json_encode(array('message' => 'Credenciales incorrectas', 'success' => false));
        }
    } else {
        http_response_code(200);
        echo json_encode(array('message' => 'Usuario no registrado',  'success' => false));
    }
}
?>
