async function ajax(url, method, body) {
						  
	  const options = {
	    method: method,
	    headers: {
	      'Content-Type': 'application/json',
	    },
	    body: JSON.stringify(body),
	  };
	  	  
	  const res = await fetch(url, options);
	  
	  // const data = await res.json();

		const data = await res;

	  if (res.ok) return data;
	  
	  else throw Error(data);
	  
}