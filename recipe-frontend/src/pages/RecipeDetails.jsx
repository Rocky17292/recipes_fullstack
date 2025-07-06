import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";

export default function RecipeDetails() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [recipe, setRecipe] = useState(null);
  const [error, setError] = useState("");

  const API_BASE = import.meta.env.VITE_API_BASE_URL;

  useEffect(() => {
    const fetchRecipe = async () => {
      try {
        const response = await axios.get(`${API_BASE}/recipes/${id}`);
        setRecipe(response.data);
      } catch (err) {
        setError("Could not load recipe details.");
        console.error(err);
      }
    };

    fetchRecipe();
  }, [id]);

  if (error) {
    return <div className="text-red-600 text-center mt-10 px-4">{error}</div>;
  }

  if (!recipe) {
    return <div className="text-center mt-10">Loading...</div>;
  }

  return (
    <div className="max-w-3xl mx-auto mt-10 p-4 bg-white shadow rounded-lg">
      <h2 className="text-3xl font-bold mb-2">{recipe.name}</h2>
      <p className="text-gray-600 mb-4">Cuisine: {recipe.cuisine}</p>

      {recipe.image && (
        <img
          src={recipe.image}
          alt={recipe.name}
          loading="lazy"
          className="w-full max-h-96 object-cover rounded-lg mb-4"
        />
      )}

      <h3 className="text-xl font-semibold mb-2">Ingredients</h3>
      <ul className="list-disc list-inside space-y-1">
        {recipe.ingredients.map((ing, idx) => (
          <li key={idx}>{ing}</li>
        ))}
      </ul>

      <button
        onClick={() => navigate("/")}
        className="mt-6 px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
      >
        ← Back to Search
      </button>
    </div>
  );
}
