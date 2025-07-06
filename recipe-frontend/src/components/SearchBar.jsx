import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

export default function SearchBar() {
  const [query, setQuery] = useState("");
  const [results, setResults] = useState([]);
  const [showDropdown, setShowDropdown] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const API_BASE = import.meta.env.VITE_API_BASE_URL;

  useEffect(() => {
    const fetchData = async () => {
      if (query.length >= 3) {
        try {
          const response = await axios.get(`${API_BASE}/recipes/search?query=${query}`);
          setResults(response.data);
          setShowDropdown(true);
          setError("");
        } catch (error) {
          console.error("Error fetching recipes:", error);
          setError("Could not fetch recipes. Please try again.");
          setResults([]);
          setShowDropdown(true);
        }
      } else {
        setResults([]);
        setShowDropdown(false);
        setError("");
      }
    };

    const debounce = setTimeout(fetchData, 300);

    return () => clearTimeout(debounce);
  }, [query]);

  return (
    <div className="relative w-full max-w-md mx-auto mt-10">
      <input
        type="text"
        placeholder="Search recipes by name or cuisine..."
        className="w-full px-4 py-2 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
      />

      {showDropdown && (
        <div className="absolute z-10 w-full bg-white border border-gray-300 rounded-lg shadow max-h-60 overflow-y-auto mt-1">
          {error && (
            <div className="px-4 py-2 text-red-500 bg-red-50 border-b border-red-200 rounded">
              {error}
            </div>
          )}
          {!error && results.length === 0 && (
            <div className="px-4 py-2 text-gray-500">No recipes found.</div>
          )}
          {results.map((recipe) => (
            <li
              key={recipe.id}
              className="px-4 py-2 hover:bg-gray-100 cursor-pointer list-none"
              onClick={() => navigate(`/recipe/${recipe.id}`)}
            >
              <div className="font-semibold">{recipe.name}</div>
              <div className="text-sm text-gray-500">Cuisine: {recipe.cuisine}</div>
            </li>
          ))}
        </div>
      )}
    </div>
  );
}
