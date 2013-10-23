### Topics.
  Trees, sorting.

### Key idea.
  Maintain the houses sorted by 'team#' and then by 'idx', keeping the relative order.
Then for each query, just find the left-side house, and the right-side house.
Be carefull, the adjacents houses can't be of different team, in this case the closest right-side is really from the left-side. See the code for clarity.
