function selectAll(selectAll)  {
  const checkboxes = document.getElementsByName('terms');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked;
  })
}