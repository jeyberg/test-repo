<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.js"></script>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"
	integrity="sha256-cCueBR6CsyA4/9szpPfrX3s49M9vUU5BgtiJj06wt/s="
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>


<title>Nova PetShop</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="page-header text-center">
		<h1>Welcome to the Nova PetShop!</h1>
	</div>

	<div class="container-fluid" id="main">
		<table class="table">
			<tr>
				<td class="col-md-6">
					<div id="tableView" class="table-responsive"
						ng-controller="petTable">
						<table class="table table-hover">
							<thead>
								<tr class="bg-primary">
									<th>Race</th>
									<th>ID</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="pet in pets">
									<td class="col-md-4">{{pet.race}}</td>
									<td class="col-md-4">{{pet.id}}</td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
				<td>
					<div>
						Type an ID to see more details: <input id="input" type="text">
						<button id="submit">Submit</button>
						<div id="paragraphs" class="well">
							<p id="species"></p>
							<p id="race"></p>
							<p id="price"></p>
							<br>
							<button id="delete">Delete</button>
						</div>

					</div>
				</td>
			</tr>
		</table>
		<button id="deleteAll">Delete All Pets</button>
		<button id="add">Add new Pet</button>
		<form action="/PetShop/logout" method="post">
			<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
			<input type="submit" value="Logout">
		</form>
		<div id="hiddenForm">
			<form id="addForm">
				Species: <input id="fSpecies" type="text"><br> Race: <input
					id="fRace" type="text"><br> Price: <input id="fPrice"
					type="text"><br> ID (must be unique!): <input id="fID"
					type="text"><br>
				<button id="send">Add</button>
			</form>
		</div>

	</div>
	<script>
        $('#paragraphs').hide()
        $('#hiddenForm').hide();

        $('#submit').click(function() {
            var id = $('#input').val();
            $('#input').html('')
            $.getJSON('http://localhost:8081/PetShop/rest/shop/browse/' + id)
                .done(function(data) {
                    $('#species').html("Species: " + data.species)
                    $('#race').html("Race: " + data.race)
                    $('#price').html("Price: " + data.price)
                    $('#paragraphs').show()
                })
                .fail(function() {
                    console.log("error")
                })
        })

        $('#delete').click(function() {
            var id = $('#input').val();
            $.ajax({
                    url: 'http://localhost:8081/PetShop/rest/shop/delete/' + id,
                    method: 'DELETE'
                })
                .done(function(response) {
                    location.reload(true);
                })
                .fail(function() {
                    console.log("error")
                })
        })

        $('#deleteAll').click(function() {
            $.ajax({
                    url: 'http://localhost:8081/PetShop/rest/shop/delete/all',
                    method: 'DELETE'
                })
                .done(function(response) {
                    location.reload(true);
                })
                .fail(function() {
                    console.log("error")
                })
        })

        $('#add').click(function() {
            $('#hiddenForm').show()
        })

        $('#send').click(function() {
            $.ajax({
                    url: 'http://localhost:8081/PetShop/rest/shop/add',
                    data: {
                        species: $('#fSpecies').val(),
                        race: $('#fRace').val(),
                        price: $('#fPrice').val(),
                        id: $('#fID').val()
                    },
                    method: 'POST'
                })
                .done(function() {
                    location.reload(true)
                })
                .fail(function() {
                    console.log("error on submit")
                })
        })

        var app = angular.module('shopModule', [])

        app.config(['$httpProvider', function($httpProvider) {
            $httpProvider.defaults.useXDomain = true
            delete $httpProvider.defaults.headers.common['X-Requested-With']
        }])

        app.controller('petTable', function($scope, $http) {
            $http.get('http://localhost:8081/PetShop/rest/shop/browse')
                .then(function(response) {
                    $scope.pets = response.data
                })
        })
    </script>
</body>
</html>