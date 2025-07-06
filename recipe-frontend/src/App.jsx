import { Routes, Route } from 'react-router-dom'
import SearchBar from './components/SearchBar'
import RecipeDetails from './pages/RecipeDetails'

export default function App() {
  return (
    <div className="min-h-screen bg-gray-50">
      <header className="p-4 bg-white shadow">
        <h1 className="text-2xl font-bold text-center text-blue-700">Recipe Finder</h1>
      </header>

      <main className="p-4">
        <Routes>
          <Route path="/" element={<SearchBar />} />
          <Route path="/recipe/:id" element={<RecipeDetails />} />
        </Routes>
      </main>
    </div>
  )
}
