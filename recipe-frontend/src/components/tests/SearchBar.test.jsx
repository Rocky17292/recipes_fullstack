import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import SearchBar from '../SearchBar';
import axios from 'axios';

jest.mock('axios');

describe('SearchBar', () => {
  test('calls API after typing 3 characters', async () => {
    axios.get.mockResolvedValueOnce({
      data: [
        { id: 1, name: 'Pizza', cuisine: 'Italian' }
      ]
    });

    render(<SearchBar />);

    const input = screen.getByPlaceholderText(/search recipes/i);
    await userEvent.type(input, 'piz');

    await waitFor(() => {
      expect(axios.get).toHaveBeenCalledWith(
        expect.stringContaining('/recipes/search?query=piz')
      );
      expect(screen.getByText(/Pizza/)).toBeInTheDocument();
    });
  });
});
