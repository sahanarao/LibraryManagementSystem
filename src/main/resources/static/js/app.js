var app = angular.module("LibMgmtApp", ['ui.bootstrap']);

app.run(function ($rootScope) {
    $rootScope.$on('scope.stored', function (event, data) {
        console.log("scope.stored", data);
    });
});

app.factory('Scopes', function ($rootScope) {
    var mem = {};
 
    return {
        store: function (key, value) {
            $rootScope.$emit('scope.stored', key);
            mem[key] = value;
        },
        get: function (key) {
            return mem[key];
        }
    };
});

app.controller("LoginController", function($scope, $http, $rootScope, $window,$location, Scopes) {		
	$scope.login = function(){
		$scope.userData = [{
	        'userName': $scope.username,
	        'password': $scope.password
	    }];
		 $scope.displayError = false;
		var isAdmin = false;
		var isUser = false;
		var userId = '';
		var role ='';
		$http.post("https://localhost:8000/login", $scope.userData).then(function(response) {
        $scope.result = response.data;
        if(response.data){
        	angular.forEach(response.data, function (value, key) {                
                role = value.role;
                userId = value.id;
                alert(role);
            });
        	if(role === "ADMIN"){
        		isAdmin = true;
        	}
        	if(role === "USER"){
        		isUser = true;        		
        	}
        	
        	if(!role){        		
        		$scope.displayError = true;
        		$location.path('/Login');
        	}
        	else{
        		$window.sessionStorage.setItem('isAdmin',isAdmin);
        		$window.sessionStorage.setItem('isUser',isUser);
        		$window.sessionStorage.setItem('userId',userId);
        		 $window.location.href = '/search';
        	}
        	
        }
       
    });
	}	
});



var app = angular.module("Search", ['ui.bootstrap']).controller("SearchController",function($scope, $http,$window) {  
//app.controller("SearchController",function($scope, $http, Scopes) {    // it's for default check box thing...

	$scope.isAdmin = $window.sessionStorage.getItem('isAdmin');
	$scope.isUser = $window.sessionStorage.getItem('isUser');
	$scope.userId = $window.sessionStorage.getItem('userId');
	alert($scope.userId);
    $scope.books = "getBooks";
    $scope.rounds = 5;
    $scope.getBooks = "Search all Book Details";
    $scope.count = "No of Books";
    $scope.addBook = "Add New Book";
    $scope.delBook = "Delete Existing Book";
    $scope.borrowBook = "Borrow Books";
    $scope.cancelBook = "Cancel Borrowed Books";
    $scope.bookCart = [{
        'book_id': '',
        'title': '',
        'publishser': '',
        'available': ''
    }];
    $scope.delCart = [];
    $scope.orderCart = {
        'borrow_id': "",
        'book_id': "book_id",
        'booking_date': "",
        'quantity': "quantity",
        'user_id':""
    };
    $scope.cancelCart;
    $scope.search = function() {
        var choice = $scope.books;
        $scope.searchBook = false;
        $scope.addBookFlag = false;
        $scope.countFlag = false;
        $scope.delBookFlag = false;
        $scope.displayStandardMessage = false;
        $scope.displayError = false;
        $scope.borrowFlag = false;
        $scope.cancelBorrow = false;
        $scope.displayBookingSucess = false;
        $scope.displayCancelSucess = false;
        // Pagination Logic
        if (choice == 'getBooks') {
            $scope.searchBook = true;
            var url = 'https://localhost:8443/api/' + choice;
            $http.get(url).
            then(function(response) {
                $scope.output = response.data;
                pagination();
            });
        } else if (choice == 'addBook') {
            // clear before adding books....
            clear();
            $scope.addBookFlag = true;

        } else if (choice == 'delBook') {
            $scope.delBookFlag = true;
            searchBooks();

        } else if (choice == 'count') {
            $scope.countFlag = true;

        } else if (choice == 'borrowBook') {
            $scope.borrowFlag = true;
            loadBooks();

        } else if (choice == 'cancelBook') {
            $scope.cancelBorrow = true;
            loadBookedThings();

        } else {
            $scope.searchBook = true;
            var url = 'https://localhost:8443/api/' + choice;
            $http.get(url).
            then(function(response) {
                $scope.output = response.data;
            });
        }
    }
 

    $scope.addRow = function() {
        console.log($scope.bookCart);
        $http.post("https://localhost:8443/api/addBook", $scope.bookCart).then(function(response) {
            if (response.status == "200") {
                $scope.addBookFlag = false;
                $scope.displayError = false;
                $scope.displayStandardMessage = true;
                clear();
            } else {
                $scope.displayError = true;
            }
        }).catch(function(response) {
            $scope.displayError = true;
        });
    };

    function paginationDel() {
        $scope.filteredTodosC = [];
        $scope.todos = [];
        $scope.currentPage = 1;
        $scope.numPerPage = 9;
        $scope.maxSize = 5;
        $scope.$watch('currentPage + numPerPage', function() {
            var begin = (($scope.currentPage - 1) * $scope.numPerPage);
            var end = begin + $scope.numPerPage;
            $scope.filteredTodosC = $scope.bookCache.slice(begin, end);
        });
    }

    function paginationBooking() {

        $scope.filteredTodosC = [];
        $scope.todos = [];
        $scope.currentPage = 1;
        $scope.numPerPage = 9;
        $scope.maxSize = 5;


        $scope.$watch('currentPage + numPerPage', function() {

            var begin = (($scope.currentPage - 1) * $scope.numPerPage);
            var end = begin + $scope.numPerPage;
            $scope.alreadyBooked = $scope.cancelCache.slice(begin, end);
        });


    }

    function loadBooks() {

        $http.get('https://localhost:8443/api/getBooks').
        then(function(response) {
            $scope.bookCache = response.data;
            paginationDel();
        });



    }

    function loadBookedThings() {
        $http.post('https://localhost:8443/api/getBorrowDetails',$scope.userId).
        then(function(response) {
            $scope.cancelCache = response.data;
            paginationBooking();
        });



    }

    function clear() {
        $scope.bookCart = [{
            'book_id': '',
            'title': '',
            'cover': '',
            'publishser': '',
            'pages': '',
            'available': ''
        }];

    }

    function pagination() {
        $scope.filteredTodos = [];
        $scope.todos = [];
        $scope.currentPage = 1;
        $scope.numPerPage = 9;
        $scope.maxSize = 5;


        $scope.$watch('currentPage + numPerPage', function() {
            var begin = (($scope.currentPage - 1) * $scope.numPerPage);
            var end = begin + $scope.numPerPage;
            $scope.filteredTodos = $scope.output.slice(begin, end);


        });


    }

    function clearDelCart() {
        $scope.delCart = [];

    }

    function searchBooks() {

        $http.get('https://localhost:8443/api/getBooks').
        then(function(response) {
            $scope.bookCache = response.data;
            paginationDel();
        });


    }
    $scope.addRows = function($event) {
        $scope.bookCart.push({});
    };

    $scope.borrowbooks = function(bcache) {
        /*
         * Pick required fields from Booking to be inserted into Order table.
         * borrow_id
         */

        $scope.orderCart.book_id = bcache.book_id;
        $scope.orderCart.quantity = bcache.pages;
        $scope.orderCart.booking_date = new Date();
        $scope.orderCart.user_id = $scope.userId;

        //
        $http.post("https://localhost:8443/api/borrowbooks", $scope.orderCart).then(function(response) {
            if (response.status == "200") {
                $scope.displayError = false;
                $scope.displayBookingSucess = true;
                clear();
            } else {
                $scope.displayError = true;
            }
        }).catch(function(response) {

            $scope.displayError = true;
        });

    }

    $scope.cancelBorrowing = function(bcache) {
        /*
         * Pick required fields from Booking to be inserted into Order table.
         * borrow_id
         */
    	

        $scope.cancelCart = bcache.borrow_id;

        alert(bcache.borrow_id);
        //
        $http.post("https://localhost:8443/api/cancelBorrowing", $scope.cancelCart).then(function(response) {
            if (response.status == "200") {
                $scope.displayError = false;
                $scope.displayCancelSucess = true;
                clear();
                loadBookedThings();
            } else {
                $scope.displayError = true;
            }
        }).catch(function(response) {

            $scope.displayError = true;
        });

    }

    $scope.deleteRows = function(bcache) {

        angular.forEach($scope.filteredTodosC, function(sel) {
            if (sel.selected) {
                $scope.delCart.push(sel);
            }
        });

        // alert(JSON.stringify($scope.delCart));
        $http.post('https://localhost:8443/api/delBook/', $scope.delCart).
        then
            (function(response) {

                if (response.status == "200") {

                    $scope.addBookFlag = false;
                    $scope.displayError = false;
                    $scope.displayStandardMessage = true;
                    clearDelCart();
                    searchBooks();
                } else {
                    $scope.displayError = true;
                }
            }).
        catch(function(response) {

            $scope.displayError = true;
        });




    }
    $scope.removeRows = function(user) {

        $scope.bookCart.splice();

    };

});

