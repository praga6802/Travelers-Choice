<?php

try {
	$servername = "localhost";
    $username = "root";
    $password = "";
    $dbname = "travel";
    $card_no = $_POST['card_no'];
    $exp_date = $_POST['exp_date'];
    $ccv = $_POST['ccv'];
	
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $stmt = $conn->prepare("INSERT INTO payment (card_no,exp_date,ccv) VALUES (:card_no, :exp_date, :ccv)");
    $stmt->bindParam(':card_no', $card_no);
    $stmt->bindParam(':exp_date', $exp_date);
    $stmt->bindParam(':ccv', $ccv);
    $stmt->execute();
    if ($stmt->execute()) { ?>
    <script type="text/javascript">
    alert("Registered Successfully");
    window.location.href = "Packages.html";
    </script>
    <?php
         } 			  
	}catch(PDOException $e)
    {
    header("location:./index.php");
    }

    ?>