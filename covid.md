{% include guides.html %}

<img width="958" alt="Covid Wireframe" src="https://user-images.githubusercontent.com/86858869/214359834-336eead4-1e4c-4c66-ba42-b6a04356dba0.png">


<script>
  const options = {
	method: 'GET',
	headers: {
		'X-RapidAPI-Key': '9d1b75d842msh20486d8bf8d5c19p1904abjsneb2943a9124c',
		'X-RapidAPI-Host': 'covid-19-coronavirus-statistics.p.rapidapi.com'
	}
};

fetch('https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/total?country=Canada', options)
	.then(response => response.json())
	.then(response => console.log(response))
	.catch(err => console.error(err));
  
  </script>
