<script>
let toppingsValue = 0;

$("input[type='checkbox']").change(function() {
  if (this.checked) {
    toppingsValue += parseInt(this.value);
  } else {
    toppingsValue -= parseInt(this.value);
  }
});

$("form").submit(() => {
  const selectedPizza = $("input[name='pizza']:checked");
  const total = toppingsValue + parseInt(selectedPizza.val());
  alert("total: " + total);
})
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<form name="order_submit" method="post" action="">
  <h1>My Pizza Cafe</h1>
  <br>


  <h3>Step 2: Select the pizza you want:</h3>

  <input type="radio" name="pizza" value="15">Small
  <input type="radio" name="pizza" value="20">Medium
  <input type="radio" name="pizza" value="25">Large


  <h3>Step 3: Select the topping you want:</h3>
  <input type="checkbox" name="topping" value="5"> Pepperoni
  <input type="checkbox" name="topping" value="7"> Sausage
  <input type="checkbox" name="topping" value="5"> Mushroom <br>
  <input type="checkbox" name="topping" value="4"> Pineapple
  <input type="checkbox" name="topping" value="4"> Black Olives
  <input type="checkbox" name="topping" value="7"> Meatball
  <br><br>

  <input type="submit" name="send" value="Submit Order">
  <input type="reset" value="Clear Entries">

</form>